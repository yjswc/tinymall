package com.cskaoyan.service;

import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.system.AdminExample;
import com.cskaoyan.mapper.AdminMapper;
import com.cskaoyan.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:39
 * @Version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> queryAdminList(String username, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) criteria.andUsernameLike("%" + username + "%");
        criteria.andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }


    @Override
    public Integer updateAdmin(Admin admin) {
        Date date = new Date();
        String password = admin.getPassword();
        admin.setPassword(MD5Utils.getMd5(password));
        admin.setUpdateTime(date);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Integer createAdmin(Admin admin) {
        Date date = new Date();
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        String password = admin.getPassword();
        admin.setPassword(MD5Utils.getMd5(password));
        return adminMapper.insertSelective(admin);
    }

    @Override
    public Integer deleteAdmin(Admin admin) {
        Date date = new Date();
        admin.setUpdateTime(date);
        admin.setDeleted(true);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public List<Admin> queryAdminList(String username) {
        AdminExample example = new AdminExample();
        example.createCriteria().andDeletedEqualTo(false).andUsernameEqualTo(username);

        return adminMapper.selectByExample(example);
    }
}
