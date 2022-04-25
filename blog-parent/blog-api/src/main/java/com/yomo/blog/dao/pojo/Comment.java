package com.yomo.blog.dao.pojo;

import lombok.Data;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
public class Comment {
    
    private Long id;
    private String content;
    private Long createDate;
    private Long articleId;
    private Long authorId;
    private Long parentId;
    private Long toUid;
    private Integer level;

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public Comment setCreateDate(Long createDate) {
        this.createDate = createDate;
        return this;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Comment setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Comment setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Comment setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getToUid() {
        return toUid;
    }

    public Comment setToUid(Long toUid) {
        this.toUid = toUid;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Comment setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
