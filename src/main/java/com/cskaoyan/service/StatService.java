package com.cskaoyan.service;

import com.cskaoyan.bean.stat.GoodsStat;
import com.cskaoyan.bean.stat.OrderStat;
import com.cskaoyan.bean.stat.UserStat;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 8:16
 * @Version: 1.0
 */
public interface StatService {
    List<UserStat> queryUserStat();

    List<OrderStat> queryOrderStat();

    List<GoodsStat> queryGoodsStat();
}
