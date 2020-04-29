package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    
    @GetMapping("list")
    public BaseRespVo getAdminList(String username, Integer page, Integer limit, String sort, String order){
        List<Admin> list = adminService.queryAdminList(username,page,limit,sort,order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }


    @PostMapping("update")
    public BaseRespVo updateAdmin(@RequestBody Admin admin) {
       adminService.updateAdmin(admin);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    public BaseRespVo createAdmin(@RequestBody Admin admin) {
       adminService.createAdmin(admin);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    public BaseRespVo deleteAdmin(@RequestBody Admin admin) {
       adminService.deleteAdmin(admin);
        return new BaseRespVo<>(0, null, "成功");
    }
}
