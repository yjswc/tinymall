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
@RequestMapping("admin/address")
public class AddressController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public BaseRespVo getAddressInfo(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        Map<String, Object> maps = userService.queryAddress(userId, name, page, limit, sort, order);
        BaseRespVo<Object> respVo = new BaseRespVo<>(0, maps, "成功");
        return respVo;
    }

}
