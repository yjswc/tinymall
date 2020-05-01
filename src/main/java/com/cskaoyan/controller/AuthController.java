package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.user.LoginToken;
import com.cskaoyan.service.AdminService;
import com.cskaoyan.service.RoleService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:49
 * @Version: 1.0
 */
@Controller
public class AuthController {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/admin/auth/login")
    @ResponseBody
    public BaseRespVo login(@RequestBody LoginToken token) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(token.getUsername(), MD5Utils.getMd5(token.getPassword())));
        Serializable id = subject.getSession().getId();
        return new BaseRespVo(0, id, "成功");
    }


    @RequestMapping("/admin/auth/info")
    @ResponseBody
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
    @ResponseBody
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseRespVo<>(0, null, "成功");
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index"; //templates/index.ftlh
    }

    @RequestMapping("unAuthc")
    public String unAuthc() {
        return "forward:/index";
    }

    //在真正之前先访问success
    //认证通过之后在访问success
    @RequestMapping("success")
    public String success() {
        return "success";
    }

    /*
     * need/perm
     * 使用这个RequiresPermissions需要aspectj支持
     * 引入aspectjweaver的支持包
     * 需要注册一个advisor
     * */
    //@RequestMapping("need/perm")
    ////@RequiresPermissions(value = {"perm1","perm2"},logical = Logical.OR)
    //public String needPerm() {
    //    Subject subject = SecurityUtils.getSubject();
    //    //Object primaryPrincipal = subject.getPrincipals().getPrimaryPrincipal();
    //
    //    return "permission";
    //}

    //@RequestMapping("noperm")
    //public String noperm() {
    //
    //    return "noperm";
    //}


}
