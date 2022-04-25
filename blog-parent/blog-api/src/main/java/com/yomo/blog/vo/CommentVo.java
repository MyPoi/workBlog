package com.yomo.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 评论视图对象
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Data
public class CommentVo {
    
    private String id;
    private UserVo author;
    private String content;
    private List<CommentVo> childrens;
    private String createDate;
    private Integer level;
    private UserVo toUser;
    
}
