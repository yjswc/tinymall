package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.goods.Comment;
import com.cskaoyan.bean.mall.Order;
import com.cskaoyan.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/28 13:06
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("list")
    public BaseRespVo getCommentList(Integer userId, Integer valueId, Integer page, Integer limit, String sort, String order) {
        List<Comment> list = commentService.queryComments(userId, valueId, page, limit, sort, order);
        HashMap<String, Object> result = new HashMap<>();
        long total = PageInfo.of(list).getTotal();
        result.put("total", total);
        result.put("items", list);
        return new BaseRespVo<>(0, result, "成功");
    }

    //@PostMapping("reply")
    //public BaseRespVo replyComment(@RequestBody HashMap<String, Object> map) {
    //    Object commentId = map.get("commentId");
    //    Integer id = (Integer) commentId;
    //    String content = (String) map.get("content");
    //    commentService.replyComment(id, content);
    //    return new BaseRespVo<>(0, null, "成功");
    //}
    @PostMapping("reply")
    public BaseRespVo replyComment(@RequestBody Integer commentId, @RequestBody String content) {
        commentService.replyComment(commentId, content);
        return new BaseRespVo<>(0, null, "成功");
    }

    @PostMapping("delete")
    public BaseRespVo deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment);
        return new BaseRespVo(0, null, "成功");
    }
}
