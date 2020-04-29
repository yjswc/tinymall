package com.cskaoyan.service;

import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.system.RoleOption;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:39
 * @Version: 1.0
 */
public interface RoleService {
    List<Role> queryRoleList(String name, Integer page, Integer limit, String sort, String order);


    List<RoleOption> queryRoleOptionList();

    Integer deleteRole(Role role);

    Integer createRole(Role role);

    Integer updateRole(Role role);

}
