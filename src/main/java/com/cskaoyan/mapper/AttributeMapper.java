package com.cskaoyan.mapper;

import com.cskaoyan.bean.goods.Attribute;
import com.cskaoyan.bean.goods.AttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface AttributeMapper {
    long countByExample(AttributeExample example);

    int deleteByExample(AttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attribute record);

    int insertSelective(Attribute record);

    int insertAttributeListSelective(List<Attribute> list);

    List<Attribute> selectByExample(AttributeExample example);

    Attribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attribute record, @Param("example") AttributeExample example);

    int updateByExample(@Param("record") Attribute record, @Param("example") AttributeExample example);

    int updateByPrimaryKeySelective(Attribute record);

    int updateByPrimaryKey(Attribute record);


}