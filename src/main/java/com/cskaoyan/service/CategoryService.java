package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.mall.pojo.Category4Wx;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface CategoryService {

    List<Category> queryCategories();

    Integer updateCategory(Category category);

    Integer createCategory(Category category);

    Integer deleteCategory(Integer id);
    List<Category> queryParentCategoryList4Wx();

    List<Category4Wx> queryFloorGoodsList4Wx();
}
