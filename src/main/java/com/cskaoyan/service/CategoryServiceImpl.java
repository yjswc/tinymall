package com.cskaoyan.service;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.mall.pojo.Category4Wx;
import com.cskaoyan.mapper.CategoryMapper;
import com.cskaoyan.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 17:26
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsMapper goodsMapper;

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

    //==========================微信小程序接口分割线======================================//

    @Override
    public List<Category> queryParentCategoryList4Wx() {
        return categoryMapper.selectParentCategoryList4Wx();
    }


    @Override
    public List<Category4Wx> queryFloorGoodsList4Wx() {
        List<Category4Wx> floorGoodsList = categoryMapper.selectFloorGoodsList4Wx();
        //查询一级类目的子类目的limit为4的展示商品，非有效商品类目删除
        Iterator<Category4Wx> iterator = floorGoodsList.iterator();
        while (iterator.hasNext()) {
            Category4Wx category4Wx = iterator.next();
            List<Integer> childCategoryList = category4Wx.getChildCategoryList();
            if (!childCategoryList.isEmpty()) {
                List<Goods> goodsList = goodsMapper.selectGoodsByCategoryIds4Wx(childCategoryList, 4);
                if (goodsList.size() > 0) category4Wx.setGoodsList(goodsList);
                continue;
            }
            iterator.remove();
        }
        return floorGoodsList;
    }

}
