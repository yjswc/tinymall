package com.cskaoyan.service;

import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.system.RoleOption;
import com.cskaoyan.bean.system.UpdatePermissionBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Map<String, Object> getPermissions(Integer roleId);

    Integer updatePermissionsByRoleId(UpdatePermissionBean permissionBean);

    Set<String> queryRoleNameSetByRoleIds(Set<Integer> roleIds);

    Set<String> queryPermissionsByRoleIds(Set<Integer> roleIds);

    Set<String> queryAPIByRoleIds(Set<Integer> roleIds);
}
