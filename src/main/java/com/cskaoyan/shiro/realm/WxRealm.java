package com.cskaoyan.shiro.realm;

import com.cskaoyan.bean.user.User;
import com.cskaoyan.service.RoleService;
import com.cskaoyan.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 14:57
 * @Version: 1.0
 */
@Component
public class WxRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
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
        List<User> users = userService.queryPwdByUserName(username);
        User user = users.get(0);
        String credential = users.size() >= 1 ? user.getPassword() : null;
        return new SimpleAuthenticationInfo(user, credential, this.getName());

    }

    /**
     * 微信用户不必授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }
}
