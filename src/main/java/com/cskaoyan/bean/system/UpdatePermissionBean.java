package com.cskaoyan.bean.system;

import lombok.Data;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 0:17
 * @Version: 1.0
 */
@Data
public class UpdatePermissionBean {
    private Integer roleId;
    private List<String> permissions;
}

