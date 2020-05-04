package com.cskaoyan.service;

import com.cskaoyan.bean.config.Config;
import com.cskaoyan.bean.config.ConfigExample;
import com.cskaoyan.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/1 20:49
 * @Version: 1.0
 */

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigMapper configMapper;

    public Integer updateConfig(HashMap<String, String> map) {
        ArrayList<Config> configs = new ArrayList<>();
        Date date = new Date();
        for (String keyName : map.keySet()) {
            Config config = Config.builder().keyName(keyName).keyValue(map.get(keyName)).updateTime(date).build();
            configs.add(config);
        }
        return configMapper.insertOrUpdateConfigByBatch(configs);
    }

    @Override
    public HashMap<String, String> getConfig(String keyword) {
        String pattern = "cskaoyanmall_" + keyword + "%";
        ConfigExample example = new ConfigExample();
        ConfigExample.Criteria criteria = example.createCriteria();
        criteria.andKeyNameLike(pattern).andDeletedEqualTo(false);
        List<Config> configs = configMapper.selectByExample(example);
        HashMap<String, String> map = new HashMap<>();
        for (Config config : configs) {
            map.put(config.getKeyName(), config.getKeyValue());
        }
        return map;
    }
}
