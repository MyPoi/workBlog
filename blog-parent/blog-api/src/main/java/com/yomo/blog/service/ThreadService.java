package com.yomo.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yomo.blog.dao.mapper.ArticleMapper;
import com.yomo.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Component
public class ThreadService {
    
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCounts = article.getViewCounts();
        Article updateArticle = new Article();
        updateArticle.setViewCounts(viewCounts + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId())
                .eq(Article::getViewCounts,viewCounts);
        articleMapper.update(updateArticle,updateWrapper);
    }
}
