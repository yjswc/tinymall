package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.GoodsService;
import com.cskaoyan.service.OrderService;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 11:37
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/dashboard")
public class DashBoardController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;


    @GetMapping("")
    public BaseRespVo getDashBoardInfo() {
        Long totalGoods = goodsService.queryTotalGoods();
        Long totalProducts = goodsService.queryTotalProducts();
        Long totalUsers = userService.queryTotalUsers();
        Long totalOrders = orderService.queryTotalOrders();
        HashMap<String, Object> result = new HashMap<>();
        result.put("goodsTotal", totalGoods);
        result.put("orderTotal", totalOrders);
        result.put("userTotal", totalUsers);
        result.put("productTotal", totalProducts);
        return new BaseRespVo<>(0, result, "成功");


    }
}
