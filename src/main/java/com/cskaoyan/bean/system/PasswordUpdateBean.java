package com.cskaoyan.bean.system;

import lombok.Data;

/**
 * @Author: Li Qing
 * @Create: 2020/5/1 12:57
 * @Version: 1.0
 */
@Data
public class PasswordUpdateBean {
    private String newPassword;
    private String newPassword2;
    private String oldPassword;
}
