package com.cskaoyan.shiro.realm;

import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.service.AdminService;
import com.cskaoyan.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 14:57
 * @Version: 1.0
 */
@Component
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;

    /**
     * 验证
     *
     * @param authenticationToken 用户登录令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        List<Admin> admins = adminService.queryAdminList(username);
        Admin admin = admins.get(0);
        String credential = admins.size() >= 1 ? admin.getPassword() : null;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, credential, this.getName());
        return authenticationInfo;
    }

    /**
     * 授权
     * 查询当前用户（已经认证通过的用户）的授权信息
     *
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        @SuppressWarnings("unchecked")
        Set<Integer> roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryRoleNameSetByRoleIds(roleIds);
        Set<String> permissions = roleService.queryPermissionsByRoleIds(roleIds);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }
}
