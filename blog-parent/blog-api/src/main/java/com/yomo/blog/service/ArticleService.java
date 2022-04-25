package com.yomo.blog.service;

import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.ArticleParam;
import com.yomo.blog.vo.param.PageParms;

/**
 * <p>
 * 文章业务接口
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
public interface ArticleService {

    /**
     * 分页查询文章列表
     *
     * @param pageParms 分页参数
     * @return 返回信息类 Result
     */
    Result listArticle(PageParms pageParms);

    /**
     * 热门文章
     * @param limit 文章数
     * @return Result对象
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit 文章数
     * @return Result对象
     */
    Result newArticle(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 根据文章id查找文章
     * @param articleId 文章id
     * @return result对象
     */
    Result findArticleById(Long articleId);

    /**
     * 写文章
     * @param articleParam 文章参数
     * @return result对象
     */
    Result publish(ArticleParam articleParam);
}
