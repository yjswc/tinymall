package com.cskaoyan.service;

import com.cskaoyan.bean.system.Log;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 14:27
 * @Version: 1.0
 */
public interface LogService {
    List<Log> queryLogList(String name, Integer page, Integer limit, String sort, String order);

}
