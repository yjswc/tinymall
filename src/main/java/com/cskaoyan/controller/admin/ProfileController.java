package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.system.PasswordUpdateBean;
import com.cskaoyan.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Li Qing
 * @Create: 2020/5/1 10:12
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/profile")
public class ProfileController {
    @Autowired
    AdminService adminService;


    @PostMapping("password")
    @RequiresPermissions("admin:profile;password")
    public BaseRespVo updatePassword(@RequestBody PasswordUpdateBean passwordUpdateBean) {
        String newPassword = passwordUpdateBean.getNewPassword();
        String oldPassword = passwordUpdateBean.getOldPassword();
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        if (oldPassword.equals(admin.getPassword())) {
            admin.setPassword(newPassword);
            adminService.updateAdmin(admin);
        }
        return new BaseRespVo(0, null, "成功");
    }
}
