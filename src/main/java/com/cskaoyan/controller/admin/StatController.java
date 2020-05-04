package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.stat.GoodsStat;
import com.cskaoyan.bean.stat.OrderStat;
import com.cskaoyan.bean.stat.UserStat;
import com.cskaoyan.service.StatService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 8:16
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/stat")
public class StatController {
    @Autowired
    StatService statService;

    @GetMapping("user")
    @RequiresPermissions("admin:stat:user")
    public BaseRespVo getUserStat() {
        List<UserStat> list = statService.queryUserStat();
        HashMap<String, Object> result = new HashMap<>();
        String[] column = {"day", "users"};
        result.put("columns", column);
        result.put("rows", list);
        return new BaseRespVo(0, result, "成功");
    }

    @GetMapping("order")
    @RequiresPermissions("admin:stat:order")
    public BaseRespVo getOrderStat() {
        List<OrderStat> list = statService.queryOrderStat();
        HashMap<String, Object> result = new HashMap<>();
        String[] column = {"day", "orders", "customers", "amount", "pcr"};
        result.put("columns", column);
        result.put("rows", list);
        return new BaseRespVo(0, result, "成功");
    }

    @GetMapping("goods")
    @RequiresPermissions("admin:stat:goods")
    public BaseRespVo getGoodsStat() {
        List<GoodsStat> list = statService.queryGoodsStat();
        HashMap<String, Object> result = new HashMap<>();
        String[] column = {"day", "orders", "products", "amount"};
        result.put("columns", column);
        result.put("rows", list);
        return new BaseRespVo(0, result, "成功");
    }
}
