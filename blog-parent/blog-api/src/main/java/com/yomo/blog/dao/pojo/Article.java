package com.yomo.blog.dao.pojo;

import lombok.Data;

/**
 * <p>
 * 文章类
 * </p>
 *
 * @author yomo
 * @since 2022/4/5
 */
public class Article {

    public static final Integer Article_TOP = 1;

    public static final Integer Article_Common = 0;

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private Integer weight;


    /**
     * 创建时间
     */
    private Long createDate;


    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Article setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public Article setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
        return this;
    }

    public Integer getViewCounts() {
        return viewCounts;
    }

    public Article setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Article setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getBodyId() {
        return bodyId;
    }

    public Article setBodyId(Long bodyId) {
        this.bodyId = bodyId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Article setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Article setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public Article setCreateDate(Long createDate) {
        this.createDate = createDate;
        return this;
    }
}
