package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Coupon;
import com.cskaoyan.bean.promotion.CouponUser;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 15:42
 * @Version: 1.0
 */
public interface CouponService {
    List<Coupon> queryCouponList(String name, Short status, Short type, Integer page, Integer limit, String sort, String order);

    Integer updateCoupon(Coupon coupon);

    Integer createCoupon(Coupon coupon);

    Integer deleteCoupon(Coupon coupon);

    Coupon queryCouponById(Integer id);

    List<CouponUser> queryCouponUserList(Integer couponId, Integer userId, Short status, Integer page, Integer limit, String sort, String order);
}
