package com.cskaoyan.mapper;

import com.cskaoyan.bean.promotion.Groupon_Rules;
import com.cskaoyan.bean.promotion.Groupon_RulesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Groupon_RulesMapper {
    long countByExample(Groupon_RulesExample example);

    int deleteByExample(Groupon_RulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groupon_Rules record);

    int insertSelective(Groupon_Rules record);

    List<Groupon_Rules> selectByExample(Groupon_RulesExample example);

    Groupon_Rules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groupon_Rules record, @Param("example") Groupon_RulesExample example);

    int updateByExample(@Param("record") Groupon_Rules record, @Param("example") Groupon_RulesExample example);

    int updateByPrimaryKeySelective(Groupon_Rules record);

    int updateByPrimaryKey(Groupon_Rules record);
}