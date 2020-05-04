package com.cskaoyan.mapper;

import com.cskaoyan.bean.mall.Brand;
import com.cskaoyan.bean.mall.BrandExample;
import com.cskaoyan.bean.goods.SimpleBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<SimpleBrand> selectSimpleInfoList();

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    //=======================微信小程序接口分割线============================//
    List<Brand> selectBrandList4Wx(Integer limit);

}