package com.yomo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.blog.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论mapper
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Mapper
public interface CommentsMapper extends BaseMapper<Comment> {
}
