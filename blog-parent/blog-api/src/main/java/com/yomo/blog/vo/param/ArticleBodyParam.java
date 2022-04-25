package com.yomo.blog.vo.param;

/**
 * <p>
 * 文章体参数
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
public class ArticleBodyParam {
    private String content;

    private String contentHtml;

    public String getContent() {
        return content;
    }

    public ArticleBodyParam setContent(String content) {
        this.content = content;
        return this;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public ArticleBodyParam setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
        return this;
    }
}
