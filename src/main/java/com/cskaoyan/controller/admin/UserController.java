package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.user.*;
import com.cskaoyan.service.UserService;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    @RequiresPermissions("admin:user:list")
    public BaseRespVo getUserInfo(String username, String mobile, Integer page, Integer limit, String sort, String order) {
        List<User> result = userService.queryUsers(username, mobile, page, limit, sort, order);
        HashMap<String, Object> map = new HashMap<>();
        long total = PageInfo.of(result).getTotal();
        map.put("items", result);
        map.put("total", total);
        BaseRespVo<Object> respVo = new BaseRespVo<>(0, map, "成功");
        return respVo;
    }

}
