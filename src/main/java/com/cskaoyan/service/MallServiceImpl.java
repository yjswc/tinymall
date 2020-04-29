package com.cskaoyan.service;

import com.cskaoyan.bean.mall.OrderGoods;
import com.cskaoyan.bean.mall.OrderGoodsExample;
import com.cskaoyan.bean.mall.*;
import com.cskaoyan.bean.system.Storage;
import com.cskaoyan.bean.user.User;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 13:01
 * @Version: 1.0
 */
@Service
public class MallServiceImpl implements MallService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<Object> queryRegions() {
        List<Object> regions = new ArrayList<>();
        return toVo(0);
    }

    @Override
    public List<Brand> queryBrands(Integer id, String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) criteria.andNameLike("%" + name + "%");
        if (id != null) criteria.andIdEqualTo(id);
        if (id != null) criteria.andIdEqualTo(id);
        criteria.andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }


    @Override
    public Integer updateBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public Integer createBrand(Brand brand) {
        Date date = new Date();
        brand.setAddTime(date);
        brand.setUpdateTime(date);
        return brandMapper.insertSelective(brand);
    }

    @Override
    public Integer deleteBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        System.out.println(brand.getUpdateTime());
        brand.setDeleted(true);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 查询类目，分级
     *
     * @return
     */
    @Override
    public List<Category> queryCategories() {

        return categoryMapper.selectChildCategoriesByPid(0);
    }

    @Override
    public Integer updateCategory(Category category) {
        category.setUpdateTime(new Date());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Integer createCategory(Category category) {
        Date date = new Date();
        category.setAddTime(date);
        category.setUpdateTime(date);
        return categoryMapper.insertSelective(category);
    }

    @Override
    public Integer deleteCategory(Integer id) {
        Category category = new Category();
        Date date = new Date();
        category.setId(id);
        category.setUpdateTime(date);
        category.setDeleted(true);
        return categoryMapper.updateByPrimaryKeySelective(category);

    }

    @Override
    public List<Order> queryOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray,
                                   Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (id != null) criteria.andIdEqualTo(id);
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (orderStatusArray.size() > 0) criteria.andOrderStatusIn(orderStatusArray);
        criteria.andDeletedEqualTo(false);
        return orderMapper.selectByExample(example);
    }

    @Override
    public Map queryOrderDetail(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderGoodsExample goodsExample = new OrderGoodsExample();
        goodsExample.createCriteria().andOrderIdEqualTo(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(goodsExample);
        User user = userMapper.selectSimpleInfo(order.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("order", order);
        map.put("orderGoods", orderGoods);
        map.put("user", user);
        return map;
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @Override
    public Integer deleteOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setUpdateTime(new Date());
        order.setDeleted(true);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 查询全国省市区，分级
     *
     * @param pid
     * @return
     */

    private List<Region> generateRegionList(Integer pid) {
        RegionExample example = new RegionExample();
        if (pid != null) example.createCriteria().andPidEqualTo(pid);
        return regionMapper.selectByExample(example);
    }

    private List<Object> toVo(Integer pid) {
        ArrayList<Object> list = new ArrayList<>();
        List<Region> regions = generateRegionList(pid);
        for (Region region : regions) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", region.getId());
            map.put("name", region.getName());
            map.put("type", region.getType());
            map.put("code", region.getCode());
            if (region.getId() < 100) map.put("children", toVo(region.getId()));
            list.add(map);
        }
        return list;
    }

    /**
     * 创建issue
     *
     * @param issue
     * @return
     */
    @Override
    public Integer createIssue(Issue issue) {
        Date date = new Date();
        issue.setAddTime(date);
        issue.setUpdateTime(date);
        return issueMapper.insertSelective(issue);
    }

    /**
     * 查询所有通用问题
     *
     * @param question
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Issue> queryIssues(String question, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        IssueExample example = new IssueExample();
        IssueExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(question))
            criteria.andQuestionLike("%" + question + "%");
        criteria.andDeletedEqualTo(false);
        return issueMapper.selectByExample(example);
    }

    /**
     * 更新问题反馈
     *
     * @param issue
     * @return
     */
    @Override
    public Integer updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    @Override
    public Integer deleteIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issue.setDeleted(true);
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    @Override
    public List<Keyword> queryKeywords(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) criteria.andKeywordLike("%" + keyword + "%");
        if (!StringUtils.isEmpty(url)) criteria.andUrlLike("%" + url + "%");
        criteria.andDeletedEqualTo(false);
        return keywordMapper.selectByExample(example);
    }

    /**
     * 创建关键字
     *
     * @param keyword
     * @return
     */

    @Override
    public Integer createKeyword(Keyword keyword) {

        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        return keywordMapper.insertSelective(keyword);

    }

    /**
     * 更新关键字
     *
     * @param keyword
     * @return
     */
    @Override
    public Integer updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        return keywordMapper.updateByPrimaryKeySelective(keyword);
    }

    /**
     * 逻辑删除关键字
     *
     * @param keyword
     * @return
     */
    @Override
    public Integer deleteKeyword(Keyword keyword) {
        keyword.setIsDefault(true);
        keyword.setUpdateTime(new Date());
        return keywordMapper.updateByPrimaryKeySelective(keyword);
    }


}
