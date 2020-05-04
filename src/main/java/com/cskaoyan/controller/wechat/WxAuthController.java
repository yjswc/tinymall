package com.cskaoyan.controller.wechat;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.user.MallToken;
import com.cskaoyan.bean.user.User;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 12:27
 * @Version: 1.0
 */
@RestController
@RequestMapping("wx/auth/")
public class WxAuthController {
    @PostMapping("login")
    public BaseRespVo wxlogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new MallToken(username, MD5Utils.getMd5(password), "wx"));
        User user = (User) subject.getPrincipal();
        Serializable id = subject.getSession().getId();
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", user);
        //令牌过期时间3小时
        result.put("tokenExpire", new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000));
        result.put("token", id);
        return new BaseRespVo(0, result, "成功");
    }
}
