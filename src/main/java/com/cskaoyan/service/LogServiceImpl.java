package com.cskaoyan.service;

import com.cskaoyan.bean.system.Log;
import com.cskaoyan.bean.system.LogExample;
import com.cskaoyan.mapper.LogMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/30 19:17
 * @Version: 1.0
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public List<Log> queryLogList(String name, Integer page, Integer limit, String sort, String order) {

        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        LogExample example = new LogExample();
        LogExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name))
            criteria.andAdminLike("%" + name + "%");
        criteria.andDeletedEqualTo(false);
        return logMapper.selectByExample(example);
    }
}
