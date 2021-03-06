package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.user.Feedback;
import com.cskaoyan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/feedback")
public class FeedbackController {

    @Autowired
    UserService userService;


    @GetMapping("list")
    @RequiresPermissions("admin:feedback:list")
    public BaseRespVo getFeekBackInfo(Integer id, String username, Integer page, Integer limit, String sort, String order) {
        List<Feedback> result = userService.queryFeedBacks(id, username, page, limit, sort, order);
        HashMap<String, Object> map = new HashMap<>();
        long total = PageInfo.of(result).getTotal();
        map.put("items", result);
        map.put("total", total);
        BaseRespVo<Object> respVo = new BaseRespVo<>(0, map, "成功");
        return respVo;
    }

}
