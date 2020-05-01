package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.promotion.Topic;
import com.cskaoyan.service.TopicService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 17:16
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("list")
    @RequiresPermissions("admin:topic:list")
    public BaseRespVo getTopicList(String tile, String subtitle, Integer page, Integer limit, String sort, String order) {
        List<Topic> list = topicService.queryTopicList(tile, subtitle, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    @PostMapping("update")
    @RequiresPermissions("admin:topic:update")
    public BaseRespVo updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("create")
    @RequiresPermissions("admin:topic:create")
    public BaseRespVo createTopic(@RequestBody Topic topic) {
        topicService.createTopic(topic);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    @RequiresPermissions("admin:topic:delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic) {
        topicService.deleteTopic(topic);
        return new BaseRespVo<>(0, null, "成功");
    }

}
