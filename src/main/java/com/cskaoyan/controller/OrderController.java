package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.*;
import com.cskaoyan.service.MallService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    MallService mallService;

    /**
     * 获取所有有效订单数据
     *
     * @return
     */
    @GetMapping("list")
    public BaseRespVo getOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        List<Order> orders = mallService.queryOrders(id, userId, orderStatusArray, page, limit, sort, order);
        HashMap<String, Object> result = new HashMap<>();
        long total = PageInfo.of(orders).getTotal();
        result.put("total", total);
        result.put("items", orders);
        return new BaseRespVo(0,result,"成功");
    }

    /**
     * 获取特定编号订单详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("detail")
    public BaseRespVo getOrderDetail(Integer id) {
        Map result = mallService.queryOrderDetail(id);
        return new BaseRespVo(0,result,"成功");
    }

    @PostMapping("delete")
    public BaseRespVo deleteOrder(Integer orderId) {
        mallService.deleteOrder(orderId);
        return new BaseRespVo(0,null,"成功");
    }

}


