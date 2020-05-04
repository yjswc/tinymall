package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.*;
import com.cskaoyan.bean.promotion.pojo.SimpleGroupon4Wx;
import com.cskaoyan.mapper.CouponMapper;
import com.cskaoyan.mapper.CouponUserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 15:44
 * @Version: 1.0
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;

    @Override
    public List<Coupon> queryCouponList(String name, Short status, Short type, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) criteria.andNameLike("%" + name + "%");
        if (status != null) criteria.andStatusEqualTo(status);
        if (type != null) criteria.andTypeEqualTo(type);
        criteria.andDeletedEqualTo(false);
        return couponMapper.selectByExample(example);
    }

    @Override
    public Integer updateCoupon(Coupon coupon) {
        coupon.setUpdateTime(new Date());
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public Integer createCoupon(Coupon coupon) {

        Date date = new Date();
        coupon.setAddTime(date);
        coupon.setUpdateTime(date);
        return couponMapper.insertSelective(coupon);
    }

    @Override
    public Integer deleteCoupon(Coupon coupon) {
        coupon.setDeleted(true);
        coupon.setUpdateTime(new Date());
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public Coupon queryCouponById(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponUser> queryCouponUserList(Integer couponId, Integer userId, Short status, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        CouponUserExample example = new CouponUserExample();
        CouponUserExample.Criteria criteria = example.createCriteria();
        if (couponId != null) criteria.andCouponIdEqualTo(couponId);
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (status != null) criteria.andStatusEqualTo(status);
        criteria.andDeletedEqualTo(false);
        return couponUserMapper.selectByExample(example);
    }

    //========================微信小程序接口分割线================================//
    @Override
    public List<SimpleGroupon4Wx> queryCouponList4Wx() {
        return couponMapper.selectCouponList4Wx();
    }
}
