package com.cskaoyan.service;

import com.cskaoyan.bean.stat.GoodsStat;
import com.cskaoyan.bean.stat.OrderStat;
import com.cskaoyan.bean.stat.UserStat;
import com.cskaoyan.mapper.OrderGoodsMapper;
import com.cskaoyan.mapper.OrderMapper;
import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 8:16
 * @Version: 1.0
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public List<UserStat> queryUserStat() {
        return userMapper.selectUserStat();
    }

    @Override
    public List<OrderStat> queryOrderStat() {
        return orderMapper.selectOrderStat();
    }

    @Override
    public List<GoodsStat> queryGoodsStat() {
        return orderGoodsMapper.selectOrderGoodsStat();
     }
}
