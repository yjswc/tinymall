package com.cskaoyan.service;

import com.cskaoyan.bean.goods.Comment;
import com.cskaoyan.bean.goods.CommentExample;
import com.cskaoyan.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 13:08
 * @Version: 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> queryComments(Integer userId, Integer valueId, Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        PageHelper.orderBy(sort + " " + order);
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (valueId != null) criteria.andValueIdEqualTo(valueId);
        return commentMapper.selectByExample(example);
    }

    @Override
    public Integer replyComment(Integer id, String content) {
        Comment comment = new Comment().builder().id(id).updateTime(new Date()).content(content).build();
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public Integer deleteComment(Comment comment) {
        comment.setUpdateTime(new Date());
        comment.setDeleted(true);
        return commentMapper.updateByPrimaryKeySelective(comment);
    }
}
