package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.user.*;
import com.cskaoyan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/history")
public class HistoryController {

    @Autowired
    UserService userService;


    @GetMapping("list")
    public BaseRespVo getHistoryInfo(Integer userId, String keyword, Integer page, Integer limit, String sort, String order) {
        List<SearchHistory> result = userService.queryHistory(userId, keyword, page, limit, sort, order);
        HashMap<String, Object> map = new HashMap<>();
        long total = PageInfo.of(result).getTotal();
        map.put("items", result);
        map.put("total", total);
        BaseRespVo<Object> respVo = new BaseRespVo<>(0, map, "成功");
        return respVo;
    }


}