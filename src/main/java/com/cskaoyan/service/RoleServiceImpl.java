package com.cskaoyan.service;

import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.system.RoleExample;
import com.cskaoyan.bean.system.RoleOption;
import com.cskaoyan.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:55
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

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
}

