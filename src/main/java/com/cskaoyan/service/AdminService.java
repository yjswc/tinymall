package com.cskaoyan.service;

import com.cskaoyan.bean.system.Admin;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 8:39
 * @Version: 1.0
 */
public interface AdminService {
    List<Admin> queryAdminList(String username, Integer page, Integer limit, String sort, String order);

    Integer updateAdmin(Admin admin);

    Integer createAdmin(Admin admin);

    Integer deleteAdmin(Admin admin);

    List<Admin> queryAdminList(String username);
}
