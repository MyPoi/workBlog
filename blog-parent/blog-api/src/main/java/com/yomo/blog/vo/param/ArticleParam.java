package com.yomo.blog.vo.param;

import com.yomo.blog.vo.CategoryVo;
import com.yomo.blog.vo.TagVo;

import java.util.List;

/**
 * <p>
 * 文章参数
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
public class ArticleParam {
    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;

    public Long getId() {
        return id;
    }

    public ArticleParam setId(Long id) {
        this.id = id;
        return this;
    }

    public ArticleBodyParam getBody() {
        return body;
    }

    public ArticleParam setBody(ArticleBodyParam body) {
        this.body = body;
        return this;
    }

    public CategoryVo getCategory() {
        return category;
    }

    public ArticleParam setCategory(CategoryVo category) {
        this.category = category;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public ArticleParam setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public List<TagVo> getTags() {
        return tags;
    }

    public ArticleParam setTags(List<TagVo> tags) {
        this.tags = tags;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleParam setTitle(String title) {
        this.title = title;
        return this;
    }
}
