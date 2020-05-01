package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Log;
import com.cskaoyan.service.LogService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 19:14
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/log")
public class LogController {
    @Autowired
    LogService logService;

    @RequestMapping("list")
    @RequiresPermissions("admin:log:list")
    public BaseRespVo getLogList(String name, Integer page, Integer limit, String sort, String order) {
        List<Log> list = logService.queryLogList(name, page, limit, sort, order);
        HashMap<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo(0, result, "成功");
    }
}
