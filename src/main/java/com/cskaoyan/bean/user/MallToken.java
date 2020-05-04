package com.cskaoyan.bean.user;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 21:10
 * @Version: 1.0
 * 登录令牌
 */
@Data
public class MallToken extends UsernamePasswordToken {
    String type;

    public MallToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
