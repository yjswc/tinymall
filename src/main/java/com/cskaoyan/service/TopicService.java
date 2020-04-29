package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Topic;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 17:17
 * @Version: 1.0
 */
public interface TopicService {
    List<Topic> queryTopicList(String tile, String subtitle, Integer page, Integer limit, String sort, String order);

    Integer updateTopic(Topic topic);

    Integer createTopic(Topic topic);

    Integer deleteTopic(Topic topic);
}
