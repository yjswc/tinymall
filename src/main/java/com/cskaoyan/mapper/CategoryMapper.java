package com.cskaoyan.mapper;

import com.cskaoyan.bean.goods.SimpleCategory;
import com.cskaoyan.bean.mall.Category;
import com.cskaoyan.bean.mall.CategoryExample;
import com.cskaoyan.bean.mall.pojo.Category4Wx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    List<Category> selectChildCategoriesByPid(Integer pid);

    List<SimpleCategory> selectSimpleCategories(Integer pid);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    //================================微信小程序分割线===========================================//

    List<Category> selectParentCategoryList4Wx();

    List<Category4Wx> selectFloorGoodsList4Wx();

}