package com.yomo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.blog.dao.pojo.ArticleBody;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章内容
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Mapper
public interface ArticleBodyMapper extends BaseMapper<ArticleBody> {
}
