package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Order;
import com.cskaoyan.bean.mall.OrderExample;
import com.cskaoyan.bean.mall.OrderGoods;
import com.cskaoyan.bean.mall.OrderGoodsExample;
import com.cskaoyan.bean.user.User;
import com.cskaoyan.mapper.OrderGoodsMapper;
import com.cskaoyan.mapper.OrderMapper;
import com.cskaoyan.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 17:28
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Order> queryOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray,
                                   Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (id != null) criteria.andIdEqualTo(id);
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (orderStatusArray.size() > 0) criteria.andOrderStatusIn(orderStatusArray);
        criteria.andDeletedEqualTo(false);
        return orderMapper.selectByExample(example);
    }

    @Override
    public Map queryOrderDetail(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderGoodsExample goodsExample = new OrderGoodsExample();
        goodsExample.createCriteria().andOrderIdEqualTo(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(goodsExample);
        User user = userMapper.selectSimpleInfo(order.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("order", order);
        map.put("orderGoods", orderGoods);
        map.put("user", user);
        return map;
    }

    /**
     * 查询所有订单数目
     *
     * @return
     */
    @Override
    public Long queryTotalOrders() {
        OrderExample example = new OrderExample();
        example.createCriteria().andDeletedEqualTo(false);
        return orderMapper.countByExample(example);
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @Override
    public Integer deleteOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setUpdateTime(new Date());
        order.setDeleted(true);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Integer refundOrder(Integer orderId, Double refundMoney) {

        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 203);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);

    }

    @Override
    public Integer shipOrder(Integer orderId, String shipChannel, String shipSn) {
        Order order = new Order().builder().updateTime(new Date()).id(orderId).shipChannel(shipChannel).shipSn(shipSn).orderStatus((short) 301).build();
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}
