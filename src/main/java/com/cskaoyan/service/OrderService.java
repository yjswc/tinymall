package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface OrderService {

    List<Order> queryOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray,
                            Integer page, Integer limit, String sort, String order);
    Map queryOrderDetail(Integer id);

    Long queryTotalOrders();

    Integer deleteOrder(Integer orderId);

    Integer refundOrder(Integer orderId, Double refundMoney);

    Integer shipOrder(Integer orderId, String shipChannel, String shipChannel1);
}
