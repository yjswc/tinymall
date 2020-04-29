package com.cskaoyan.service;

import com.cskaoyan.bean.promotion.Topic;
import com.cskaoyan.bean.promotion.TopicExample;
import com.cskaoyan.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 17:17
 * @Version: 1.0
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;

    @Override
    public List<Topic> queryTopicList(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        TopicExample example = new TopicExample();
        TopicExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(title)) criteria.andTitleLike("%" + title + "%");
        if (!StringUtils.isEmpty(subtitle)) criteria.andSubtitleLike("%" + subtitle + "%");
        criteria.andDeletedEqualTo(false);
        return topicMapper.selectByExampleWithBLOBs(example);
    }
    
    @Override
    public Integer updateTopic(Topic topic) {
        Date date = new Date();
        topic.setUpdateTime(date);
        return topicMapper.updateByPrimaryKeySelective(topic);
    }

    @Override
    public Integer createTopic(Topic topic) {
        Date date = new Date();
        topic.setAddTime(date);
        topic.setUpdateTime(date);
        return topicMapper.insertSelective(topic);
    }

    @Override
    public Integer deleteTopic(Topic topic) {
        Date date = new Date();
        topic.setUpdateTime(date);
        topic.setDeleted(true);
        return topicMapper.updateByPrimaryKeySelective(topic);
    }

}
