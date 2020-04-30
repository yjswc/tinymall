package com.cskaoyan.service;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.bean.goods.*;
import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.mall.Keyword;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: Li Qing
 * @Create: 2020/4/27 11:14
 * @Version: 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    SpecificationMapper specificationMapper;
    @Autowired
    KeywordMapper keywordMapper;


    /**
     * 获取商品信息列表
     *
     * @param goodsId
     * @param goodSn
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */

    @Override
    public List<Goods> queryGoodsList(Integer goodsId, String goodSn, String name,
                                      Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        GoodsExample example = new GoodsExample();
        example.setOrderByClause(sort + " " + order);
        GoodsExample.Criteria criteria = example.createCriteria();
        if (goodsId != null) criteria.andIdEqualTo(goodsId);
        if (!StringUtils.isEmpty(goodSn)) criteria.andGoodsSnLike("%" + goodSn + "%");
        if (!StringUtils.isEmpty(name)) criteria.andGoodsSnLike("%" + name + "%");
        criteria.andDeletedEqualTo(false);
        List<Goods> list = goodsMapper.selectByExampleWithBLOBs(example);
        System.out.println(list);
        return list;
    }

    @Override
    public Map<String, Object> queryCatAndBrand() {
        Map<String, Object> map = new HashMap<>();
        List<SimpleCategory> categoryList = categoryMapper.selectSimpleCategories(0);
        List<SimpleBrand> brandList = brandMapper.selectSimpleInfoList();
        map.put("categoryList", categoryList);
        map.put("brandList", brandList);
        return map;
    }

    @Override
    public Map<String, Object> queryGoodsDetail(Integer goodsId) {
        Map<String, Object> map = new HashMap<>();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        Integer categoryId = goods.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Integer pid = category.getPid();
        Integer[] categoryIds = {pid, categoryId};
        //获取goods的详细信息
        List<Attribute> attributes = selectAttributeList(goodsId);
        List<Product> products = selectProductList(goodsId);
        List<Specification> specifications = selectSpecificatinList(goodsId);
        map.put("categoryIds", categoryIds);
        map.put("goods", goods);
        map.put("attributes", attributes);
        map.put("specifications", specifications);
        map.put("products", products);
        return map;
    }

    /**
     * 查询关于特定goodid规格的列表信息
     *
     * @param goodsId
     * @return
     */

    private List<Specification> selectSpecificatinList(Integer goodsId) {
        SpecificationExample example = new SpecificationExample();
        example.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsId);
        return specificationMapper.selectByExample(example);
    }

    /**
     * 查询特定goodsid参数列表
     *
     * @param goodsId
     * @return
     */
    private List<Attribute> selectAttributeList(Integer goodsId) {
        AttributeExample example = new AttributeExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return attributeMapper.selectByExample(example);
    }

    /**
     * 查找goodsid的库存信息
     *
     * @param goodsId
     * @return
     */
    private List<Product> selectProductList(Integer goodsId) {
        ProductExample example = new ProductExample();
        example.createCriteria().andDeletedEqualTo(false).andGoodsIdEqualTo(goodsId);
        return productMapper.selectByExample(example);
    }

    /**
     * 更新商品相关信息
     * 更新关键词
     * 库存，规格，参数都需要更新，采用先删后增的方式(//FIXME:不算是一种好方式，但是在此处省事儿)
     *
     * @param goodsIntotal
     * @return
     */
    @Override
    public Integer updateGoods(GoodsInTotal goodsIntotal) {
        Goods goods = goodsIntotal.getGoods();
        List<Product> products = goodsIntotal.getProducts();
        List<Attribute> attributes = goodsIntotal.getAttributes();
        List<Specification> specifications = goodsIntotal.getSpecifications();
        //依次更新时间,然后更新相关数据库
        Date date = new Date();
        goods.setUpdateTime(date);
        insertAndUpdateKeywords(goods);
        Integer goodsId = goods.getId();
        //逻辑删除相关参数
        deleteAttribute(goodsId);

        if (!(attributes == null || attributes.isEmpty())) {
            for (Attribute attribute : attributes) {
                attribute.setUpdateTime(date);
            }
            attributeMapper.insertAttributeListSelective(attributes);
        }
        deleteProducts(goodsId);
        if (!(products == null || products.isEmpty())) {
            for (Product product : products) {
                product.setUpdateTime(date);
            }
            productMapper.insertProductListSelective(products);
        }
        deleteSpecification(goodsId);
        if (!(specifications == null || specifications.isEmpty())) {
            for (Specification specification : specifications) {
                specification.setUpdateTime(date);
            }
            specificationMapper.insertSpecificationListSelective(specifications);
        }
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }


    /**
     * 新增商品以及对应的参数，库存，规格等
     * 新增除了关键字可能会重复外，参数，库存，规格均不会重复
     * 开启事务
     *
     * @param goods
     * @param attributes
     * @param products
     * @param specifications
     * @return
     */
    @Override
    @Transactional
    public Integer createGoods(GoodsInTotal goodsIntotal) {
        Goods goods = goodsIntotal.getGoods();
        List<Product> products = goodsIntotal.getProducts();
        List<Attribute> attributes = goodsIntotal.getAttributes();
        List<Specification> specifications = goodsIntotal.getSpecifications();
        //更新关键词
        insertAndUpdateKeywords(goods);
        //依次更新时间,然后更新相关数据库
        Date date = new Date();
        goods.setUpdateTime(date);
        //插入goods信息，此时已经生成对应的id
        goodsMapper.insertSelective(goods);
        Integer goodsId = goods.getId();
        if (!(attributes == null || attributes.isEmpty())) {
            for (Attribute attribute : attributes) {
                attribute.setGoodsId(goodsId);
                attribute.setAddTime(date);
                attribute.setUpdateTime(date);
            }
            attributeMapper.insertAttributeListSelective(attributes);
        }
        if (!(products == null || products.isEmpty())) {
            for (Product product : products) {
                product.setGoodsId(goodsId);
                product.setAddTime(date);
                product.setUpdateTime(date);
            }
            productMapper.insertProductListSelective(products);
        }
        if (!(specifications == null || specifications.isEmpty())) {
            for (Specification specification : specifications) {
                specification.setGoodsId(goodsId);
                specification.setAddTime(date);
                specification.setUpdateTime(date);
            }
            specificationMapper.insertSpecificationListSelective(specifications);
        }
        return 200;
    }

    /**
     * 此处删除商品仍然保留其参数，规格，库存信息
     *
     * @param goods
     * @return
     */

    @Override
    public Integer deleteGoods(Goods goods) {
        Date date = new Date();
        goods.setUpdateTime(date);
        goods.setDeleted(true);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 更新或插入商品关键词
     *
     * @param goods
     * @return
     */

    private Integer insertAndUpdateKeywords(Goods goods) {
        String keywords = goods.getKeywords();
        if (!StringUtils.isEmpty(keywords)) {
            ArrayList<Keyword> list = new ArrayList<>();
            Date date = new Date();
            Boolean isHot = goods.getIsHot();
            String[] keywordList = keywords.split(",");
            for (String string : keywordList) {
                Keyword keyword = new Keyword().builder()
                        .addTime(date).updateTime(date).keyword(string)
                        .isHot(isHot).deleted(false).build();
                list.add(keyword);
            }
            //批量有则更新，无则插入
            return keywordMapper.insertAndUpdate(list);
        }
        return 404;
    }

    /**
     * 逻辑删除所有相关商品参数
     *
     * @param goodsId
     * @return
     */
    private Integer deleteAttribute(Integer goodsId) {
        AttributeExample example = new AttributeExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        return attributeMapper.deleteByExample(example);
    }

    /**
     * 删除所有产品
     *
     * @param goodsId
     * @return
     */
    private Integer deleteProducts(Integer goodsId) {
        ProductExample example = new ProductExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        return productMapper.deleteByExample(example);
    }

    /**
     * 删除规格
     *
     * @param goodsId
     * @return
     */
    private Integer deleteSpecification(Integer goodsId) {
        SpecificationExample example = new SpecificationExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        return specificationMapper.deleteByExample(example);
    }

    /**
     * 查询所有商品数目
     *
     * @return
     */
    @Override
    public Long queryTotalGoods() {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andDeletedEqualTo(false);
        return goodsMapper.countByExample(example);
    }

    /**
     * 查询所有货品数目
     *
     * @return
     */
    @Override
    public Long queryTotalProducts() {
        ProductExample example = new ProductExample();
        example.createCriteria().andDeletedEqualTo(false);
        return productMapper.countByExample(example);
    }
}

