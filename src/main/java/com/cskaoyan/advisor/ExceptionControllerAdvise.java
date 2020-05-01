package com.cskaoyan.advisor;

import com.cskaoyan.bean.BaseRespVo;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Li Qing
 * @Create: 2020/5/1 15:51
 * @Version: 1.0
 * 处理shiro登录或授权异常信息
 */
@RestControllerAdvice
public class ExceptionControllerAdvise {
    /**
     * 处理登录异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({AuthenticationException.class, IncorrectCredentialsException.class})
    public BaseRespVo handleAuthenException(ShiroException e) {
        e.printStackTrace();
        return new BaseRespVo(605, null, "账号或密码不正确");
    }

    /**
     * 处理权限异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler({UnauthorizedException.class})
    public BaseRespVo handleAuthorException(ShiroException e) {
        e.printStackTrace();
        return new BaseRespVo(401, null, "未被授权");
    }
}
