package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.mall.*;
import com.cskaoyan.service.MallService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;


/**
 * @Author: Li Qing
 * @Create: 2020/4/25 12:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/issue")
public class IssueController {
    @Autowired
    MallService mallService;

    @PostMapping("create")
    public BaseRespVo createIssue(@RequestBody Issue issue) {
        mallService.createIssue(issue);
        return new BaseRespVo(0, null, "成功");
    }


    /**
     * 查问题反馈列表
     *
     * @param question
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("list")
    public BaseRespVo getIssues(String question, Integer page, Integer limit, String sort, String order) {
        List<Issue> list = mallService.queryIssues(question, page, limit, sort, order);
        long total = PageInfo.of(list).getTotal();
        HashMap<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo(0, result, "成功");
    }

    /**
     * 更改问题反馈
     *
     * @param issue
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateIssue(@RequestBody Issue issue) {
        mallService.updateIssue(issue);
        return new BaseRespVo(0, null, "成功");
    }

    /**
     * 逻辑删除问答
     *
     * @param issue
     * @return
     */

    @PostMapping("delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue) {
        mallService.deleteIssue(issue);
        return new BaseRespVo(0, null, "成功");
    }

}


