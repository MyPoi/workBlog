package com.yomo.blog.controller;

import com.yomo.blog.common.aop.LogAnnotation;
import com.yomo.blog.common.cache.Cache;
import com.yomo.blog.service.ArticleService;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.ArticleParam;
import com.yomo.blog.vo.param.PageParms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * TODO:文章controller层，json数据进行交互
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页文章列表
     *
     * @param pageParms
     * @return
     */
    @PostMapping
    @LogAnnotation(module = "文章", operator = "获取文章列表")
    @Cache(expire =5 * 60 * 1000, name = "list_article")
    public Result listArticle(@RequestBody PageParms pageParms) {
        return articleService.listArticle(pageParms);
    }

    /**
     * 热门文章
     *
     * @return Result对象
     */
    @PostMapping("hot")
    @Cache(expire =5 * 60 * 1000, name = "hot_article")
    public Result hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 最新文章
     *
     * @return Result对象
     */
    @PostMapping("new")
    public Result newArticle() {
        int limit = 5;
        return articleService.newArticle(limit);
    }

    /**
     * 文章归档
     *
     * @return Result对象
     */
    @PostMapping("listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    /**
     * 根据id查找具体文章
     *
     * @param articleId 文章id
     * @return result对象
     */
    @PostMapping("view/{id}")
    public Result findArticle(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    /**
     * 写文章
     *
     * @param articleParam 文章参数
     * @return result对象
     */
    @PostMapping("publish")
    public Result publishArticle(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }

}
