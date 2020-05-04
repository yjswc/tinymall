package com.cskaoyan.service;

import com.cskaoyan.bean.mall.Issue;
import com.cskaoyan.bean.mall.IssueExample;
import com.cskaoyan.mapper.IssueMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/5/4 17:34
 * @Version: 1.0
 */
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;

    /**
     * 创建issue
     *
     * @param issue
     * @return
     */
    @Override
    public Integer createIssue(Issue issue) {
        Date date = new Date();
        issue.setAddTime(date);
        issue.setUpdateTime(date);
        return issueMapper.insertSelective(issue);
    }

    /**
     * 查询所有通用问题
     *
     * @param question
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Issue> queryIssues(String question, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        IssueExample example = new IssueExample();
        IssueExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(question))
            criteria.andQuestionLike("%" + question + "%");
        criteria.andDeletedEqualTo(false);
        return issueMapper.selectByExample(example);
    }

    /**
     * 更新问题反馈
     *
     * @param issue
     * @return
     */
    @Override
    public Integer updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    @Override
    public Integer deleteIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issue.setDeleted(true);
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

}
