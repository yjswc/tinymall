package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.promotion.Advertisement;
import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.system.RoleOption;
import com.cskaoyan.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:54
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("options")
    public BaseRespVo getRolesOption() {
        List<RoleOption> result = roleService.queryRoleOptionList();
        return new BaseRespVo<>(0, result, "成功");
    }

    @GetMapping("list")
    public BaseRespVo getRoleList(String name, Integer page, Integer limit, String sort, String order) {
        List<Role> list = roleService.queryRoleList(name, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    public BaseRespVo updateAd(@RequestBody Role role) {
       roleService.updateRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    public BaseRespVo createAd(@RequestBody Role role) {
       roleService.createRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    public BaseRespVo deleteAd(@RequestBody Role role) {
       roleService.deleteRole(role);
        return new BaseRespVo<>(0, null, "成功");
    }
}
