package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.promotion.CouponUser;
import com.cskaoyan.bean.promotion.Coupon;
import com.cskaoyan.service.CouponService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 15:42
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping("list")
    @RequiresPermissions("admin:coupon:list")
    public BaseRespVo getCouponList(String name, Short status, Short type,
                                    Integer page, Integer limit, String sort, String order) {
        List<Coupon> list = couponService.queryCouponList(name, status, type, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:coupon:update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon) {
        couponService.updateCoupon(coupon);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:coupon:create")
    public BaseRespVo createCoupon(@RequestBody Coupon coupon) {
        couponService.createCoupon(coupon);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:coupon:delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon) {
        couponService.deleteCoupon(coupon);
        return new BaseRespVo<>(0, null, "成功");
    }

    @GetMapping("read")
    @RequiresPermissions("admin:coupon:read")
    public BaseRespVo getCouponList(Integer id) {
        Coupon result = couponService.queryCouponById(id);
        return new BaseRespVo<>(0, result, "成功");
    }

    @GetMapping("listuser")
    @RequiresPermissions("admin:coupon:listUser")
    public BaseRespVo getCoupon_UserList(Integer couponId, Integer userId, Short status, Integer page, Integer limit, String sort, String order) {
        List<CouponUser> list = couponService.queryCouponUserList(couponId, userId, status, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

}
