package com.cskaoyan.service;

import com.cskaoyan.bean.goods.Comment;

import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 13:08
 * @Version: 1.0
 */
public interface CommentService {
    List<Comment> queryComments(Integer userId, Integer valueId, Integer page, Integer limit, String sort, String order);

    Integer replyComment(Integer id, String content);

    Integer deleteComment(Comment comment);
}
