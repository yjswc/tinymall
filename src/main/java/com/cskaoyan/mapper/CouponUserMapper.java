package com.cskaoyan.mapper;

import com.cskaoyan.bean.promotion.CouponUser;
import com.cskaoyan.bean.promotion.CouponUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponUserMapper {
    long countByExample(CouponUserExample example);

    int deleteByExample(CouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    List<CouponUser> selectByExample(CouponUserExample example);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByExample(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);
}