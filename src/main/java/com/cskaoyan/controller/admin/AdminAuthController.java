package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.user.MallToken;
import com.cskaoyan.service.AdminService;
import com.cskaoyan.service.RoleService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:49
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/auth")
public class AdminAuthController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;

    @RequestMapping("login")
    public BaseRespVo adminlogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new MallToken(username, MD5Utils.getMd5(password), "admin"));
        Serializable id = subject.getSession().getId();
        return new BaseRespVo(0, id, "成功");
    }


    @RequestMapping("info")
    public BaseRespVo info() {
        HashMap<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        result.put("name", admin.getUsername());
        result.put("avatar", admin.getAvatar());
        Set roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryRoleNameSetByRoleIds(roleIds);
        result.put("roles", roles);
        result.put("perms", roleService.queryAPIByRoleIds(roleIds));
        //result.put("perms", roleService.queryPermissionsByRoleIds(roleIds));
        return new BaseRespVo<>(0, result, "成功");
    }

    @RequestMapping("/admin/auth/logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseRespVo<>(0, null, "成功");
    }


}
