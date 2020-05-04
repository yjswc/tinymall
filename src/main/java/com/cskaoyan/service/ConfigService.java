package com.cskaoyan.service;

import java.util.HashMap;

/**
 * @Author: Li Qing
 * @Create: 2020/5/1 20:49
 * @Version: 1.0
 */
public interface ConfigService {
    Integer updateConfig(HashMap<String, String> map);

    HashMap<String, String> getConfig(String express);
}
