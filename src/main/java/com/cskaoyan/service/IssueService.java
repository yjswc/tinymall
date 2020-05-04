package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Issue;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 15:37
 * @Version: 1.0
 */
public interface IssueService {

    Integer createIssue(Issue issue);

    List<Issue> queryIssues(String question, Integer page, Integer limit, String sort, String order);

    Integer updateIssue(Issue issue);

    Integer deleteIssue(Issue issue);

}
