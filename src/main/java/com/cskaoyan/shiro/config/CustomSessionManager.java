package com.cskaoyan.shiro.config;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 16:19
 * @Version: 1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    //后台管理端token
    public static final String AdminHeaderReference = "X-cskaoyan-mall-Admin-Token";
    //小程序端token
    public static final String WxHeaderReference = "X-Litemall-Token";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String header;
        if (httpServletRequest.getRequestURI().contains("wx")) header = WxHeaderReference;
        else header = AdminHeaderReference;
        String sessionId = httpServletRequest.getHeader(header);
        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }
}

