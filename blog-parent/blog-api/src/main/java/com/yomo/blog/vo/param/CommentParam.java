package com.yomo.blog.vo.param;

import lombok.Data;

/**
 * <p>
 * 评论参数
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Data
public class CommentParam {
    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
