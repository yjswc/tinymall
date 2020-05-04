package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.Order;
import com.cskaoyan.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    OrderService orderService;

    /**
     * 获取所有有效订单数据
     *
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("admin:order:list")
    public BaseRespVo getOrders(Integer id, Integer userId, ArrayList<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        List<Order> orders = orderService.queryOrders(id, userId, orderStatusArray, page, limit, sort, order);
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
    @RequiresPermissions("admin:order:detail")
    public BaseRespVo getOrderDetail(Integer id) {
        Map result = orderService.queryOrderDetail(id);
        return new BaseRespVo(0,result,"成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:order:delete")
    public BaseRespVo deleteOrder(Integer orderId) {
        orderService.deleteOrder(orderId);
        return new BaseRespVo(0,null,"成功");
    }
    @PostMapping("refund")
    public BaseRespVo refundOrder(Integer orderId,Double refundMoney){
        orderService.refundOrder(orderId,refundMoney);
        return new BaseRespVo(0,null,"成功");
    }
    @PostMapping("ship")
    public BaseRespVo shipOrder(Integer orderId,String shipChannel, String shipSn ){
        orderService.shipOrder(orderId,shipChannel,shipChannel);
        return new BaseRespVo(0,null,"成功");
    }


}


