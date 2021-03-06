package com.cskaoyan.mapper;

import com.cskaoyan.bean.promotion.Coupon;
import com.cskaoyan.bean.promotion.CouponExample;
import com.cskaoyan.bean.promotion.pojo.SimpleGroupon4Wx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    //=================微信小程序接口分割线=========================//

    List<SimpleGroupon4Wx> selectCouponList4Wx();

}