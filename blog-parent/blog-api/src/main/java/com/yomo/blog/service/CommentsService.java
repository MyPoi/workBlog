package com.yomo.blog.service;

import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.CommentParam;

/**
 * <p>
 * 评论业务类
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
public interface CommentsService {
    /**
     * 根据文章id查询评论
     * @param articleId 文章id
     * @return result对象
     */
    Result commentsByArticleId(Long articleId);

    /**
     * 评论
     * @param commentParam 评论参数
     * @return result对象
     */
    Result comment(CommentParam commentParam);
}
