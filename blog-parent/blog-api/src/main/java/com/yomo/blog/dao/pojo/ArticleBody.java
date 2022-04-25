package com.yomo.blog.dao.pojo;

import lombok.Data;

/**
 * <p>
 * 文章主体
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
public class ArticleBody {
    
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;

    public Long getId() {
        return id;
    }

    public ArticleBody setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleBody setContent(String content) {
        this.content = content;
        return this;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public ArticleBody setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
        return this;
    }

    public Long getArticleId() {
        return articleId;
    }

    public ArticleBody setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }
}
