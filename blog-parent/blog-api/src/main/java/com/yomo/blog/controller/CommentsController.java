package com.yomo.blog.controller;

import com.yomo.blog.dao.pojo.Comment;
import com.yomo.blog.service.CommentsService;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@RestController
@RequestMapping("comments")
public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;
    
    @GetMapping("article/{id}")
    public Result queryComments(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);
    }
    
    @PostMapping("create/change")
    public Result addComment(@RequestBody CommentParam commentParam){
        return commentsService.comment(commentParam);
    }
    
}
