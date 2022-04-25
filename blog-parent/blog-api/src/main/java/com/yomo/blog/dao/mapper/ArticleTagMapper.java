package com.yomo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.blog.dao.pojo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签mapper
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
}
