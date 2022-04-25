package com.yomo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yomo.blog.dao.dos.Archives;
import com.yomo.blog.dao.mapper.ArticleBodyMapper;
import com.yomo.blog.dao.mapper.ArticleMapper;
import com.yomo.blog.dao.mapper.ArticleTagMapper;
import com.yomo.blog.dao.pojo.Article;
import com.yomo.blog.dao.pojo.ArticleBody;
import com.yomo.blog.dao.pojo.ArticleTag;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.*;
import com.yomo.blog.utils.UserThreadLocal;
import com.yomo.blog.vo.ArticleBodyVo;
import com.yomo.blog.vo.ArticleVo;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.TagVo;
import com.yomo.blog.vo.param.ArticleParam;
import com.yomo.blog.vo.param.PageParms;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 文章业务实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private ArticleTagMapper articleTagMapper;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result listArticle(PageParms pageParms) {
        Page<Article> page = new Page<>(pageParms.getPage(), pageParms.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(page,
                pageParms.getCategoryId(),
                pageParms.getTagId(),
                pageParms.getYear(),
                pageParms.getMonth());
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records, true, true));
    }

    // @Override
    // public Result listArticle(PageParms pageParms) {
    //     // 分页查询article得到结果
    //     Page<Article> page = new Page<>(pageParms.getPage(), pageParms.getPageSize());
    //     LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
    //     if (pageParms.getCategoryId() != null){
    //         // and category_id = #{categoryId}}
    //         queryWrapper.eq(Article::getCategoryId,pageParms.getCategoryId());
    //     }
    //     List<Long> articleIdList = new ArrayList<>(); 
    //     if (pageParms.getTagId() != null){
    //         /**
    //          * 1、加入标签，条件查询
    //          * 2、我们发现没有tag_id 同时一篇文章可以有多个标签
    //          * 3、article_tag article_id 1 : n tag_id
    //          */
    //         LambdaQueryWrapper<ArticleTag> articleTagQueryWrapper = new LambdaQueryWrapper<>();
    //         articleTagQueryWrapper.eq(ArticleTag::getTagId,pageParms.getTagId());
    //         List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagQueryWrapper);
    //         articleTags.forEach(articleTag -> articleIdList.add(articleTag.getArticleId()));
    //         if (articleIdList.size() > 0) queryWrapper.in(Article::getId,articleIdList);
    //     }
    //     // order by create_date desc 根据时间排序,是否置顶排序
    //     // queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
    //     Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate));
    //     List<Article> records = articlePage.getRecords();
    //     // 不能直接返回，我们获取的是数据库里的对象，我们需要将其转变成vo（视图对象）将其封装一下
    //     List<ArticleVo> articleVoList = copyList(records, true, true);
    //     return Result.success(articleVoList);
    // }

    @Override
    public Result hotArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // queryWrapper.orderByDesc(Article::getViewCounts)
        //         .select(Article::getId,Article::getTitle)
        //         .last("limit "+limit);
        // queryWrapper.select(Article::getId,Article::getTitle);
        // queryWrapper.last("limit "+limit);
        List<Article> articles = articleMapper.selectList(queryWrapper.orderByDesc(Article::getViewCounts)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit));
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        List<Article> articles = articleMapper.selectList(queryWrapper.orderByDesc(Article::getCreateDate)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit));
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1、根据id查询文章信息
         * 2、根据bodyID和categoryID做关联查询
         */
        Article article = this.articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true, true, true);
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }

    @Override
    public Result publish(ArticleParam articleParam) {
        /**
         * 1、发布文章 目的 构建Article对象
         * 2、作者id，当前的登录用户 threadLocal
         * 3、要使用threadLocal得将此接口加入到登录拦截器里面
         * 4、要将标签加入到关联列表中国
         * 5、body 内容存储 article bodyId
         */
        SysUser sysUser = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(sysUser.getId())
                .setWeight(Article.Article_Common)
                .setViewCounts(0)
                .setTitle(articleParam.getTitle())
                .setSummary(articleParam.getSummary())
                .setCommentCounts(0)
                .setCreateDate(System.currentTimeMillis())
                .setCategoryId(Long.parseLong(articleParam.getCategory().getId()));

        // 插入生成文章id
        this.articleMapper.insert(article);

        // tag
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            tags.forEach(tagVo -> {
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(Long.parseLong(tagVo.getId())).setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            });
        }

        // body
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId())
                .setContent(articleParam.getBody().getContent())
                .setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);

        // 之所以不先插入内容是因为没有文章id，而内容需要文章id作为对应
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        // Map<String, String> map = new HashMap<>();
        // map.put("id",String.valueOf(article.getId()));
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        Set<String> keys = stringRedisTemplate.keys("list_article:ArticleController:listArticle:" + "*");
        stringRedisTemplate.delete(keys);
        return Result.success(articleVo);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        records.forEach(article -> articleVoList.add(copy(article, isTag, isAuthor, false, false)));
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        records.forEach(article -> articleVoList.add(copy(article, isTag, isAuthor, isBody, isCategory)));
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        // 并不是所有的接口都需要标签，作者信息
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (isBody) {
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    private ArticleBodyVo findArticleBodyById(Long bodyId) {

        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
