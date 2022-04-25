package com.yomo.blog.dao.pojo;

/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */

public class ArticleTag {
    private Long id;
    private Long articleId;
    private Long tagId;

    public Long getId() {
        return id;
    }

    public ArticleTag setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getArticleId() {
        return articleId;
    }

    public ArticleTag setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public Long getTagId() {
        return tagId;
    }

    public ArticleTag setTagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }
}
