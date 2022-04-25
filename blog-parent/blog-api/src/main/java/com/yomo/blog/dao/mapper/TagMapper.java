package com.yomo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询id列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热标签 前limit条
     * @param limit 条数
     * @return 标签列表
     */
    List<Long> findHotTagIds(int limit);

    /**
     * 查询标签根据tagId
     * @param tagIds 标签id
     * @return 标签列表
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
