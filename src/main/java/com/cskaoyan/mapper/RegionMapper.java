package com.cskaoyan.mapper;

import com.cskaoyan.bean.mall.Region;
import com.cskaoyan.bean.mall.RegionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
     String selectRegionName(Integer code);

    long countByExample(RegionExample example);

    int deleteByExample(RegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    List<Region> selectByExample(RegionExample example);

    Region selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}