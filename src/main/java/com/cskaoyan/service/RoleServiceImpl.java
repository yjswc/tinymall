package com.cskaoyan.service;

import com.cskaoyan.bean.system.Permission;
import com.cskaoyan.bean.system.*;
import com.cskaoyan.mapper.PermissionMapper;
import com.cskaoyan.mapper.PermissionRoleMapper;
import com.cskaoyan.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:55
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionRoleMapper permissionRoleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Role> queryRoleList(String name, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) criteria.andNameLike("%" + name + "%");
        criteria.andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<RoleOption> queryRoleOptionList() {
        return roleMapper.selectRoleOptions();
    }


    @Override
    public Integer updateRole(Role role) {
        Date date = new Date();
        role.setUpdateTime(date);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 查询特定roleId的details
     *
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> getPermissions(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        List<String> assignedPermissions = getAllPermissionsByRoleId(roleId);
        List<Permission> systemPermissions = permissionMapper.selectPermissionListByPid(0);
        map.put("systemPermissions", systemPermissions);
        map.put("assignedPermissions", assignedPermissions);
        return map;
    }

    @Override
    public Integer updatePermissionsByRoleId(UpdatePermissionBean permissionBean) {
        PermissionRoleExample example = new PermissionRoleExample();
        Integer roleId = permissionBean.getRoleId();
        example.createCriteria().andRoleIdEqualTo(roleId);
        permissionRoleMapper.deleteByExample(example);
        List<String> permissions = permissionBean.getPermissions();
        return permissionRoleMapper.insertPermissionsByRoleId(roleId, permissions);
    }

    @Override
    public Integer createRole(Role role) {
        Date date = new Date();
        role.setAddTime(date);
        role.setUpdateTime(date);
        return roleMapper.insertSelective(role);
    }

    @Override
    public Integer deleteRole(Role role) {
        Date date = new Date();
        role.setUpdateTime(date);
        role.setDeleted(true);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 获取所有权限列表
     *
     * @param roleId
     * @return
     */
    private List<String> getAllPermissionsByRoleId(Integer roleId) {
        if (roleId == 1) return permissionMapper.selectPermissionList();
        return permissionRoleMapper.selectPermissionList(roleId);
    }
}

