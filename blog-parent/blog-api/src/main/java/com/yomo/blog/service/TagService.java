package com.yomo.blog.service;

import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.TagVo;

import java.util.List;

/**
 * <p>
 * 标签业务接口
 * </p>
 *
 * @author yomo
 * @since 2022/4/7
 */
public interface TagService {

    /**
     * 根据文章id查询标签
     * @param articleId 文章id
     * @return TagVo列表
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 查询最热标签
     * @param limit 取limit条
     * @return result对象
     */
    Result hots(int limit);

    /**
     * 查询所有的标签
     * @return result对象
     */
    Result finAll();

    /**
     * 更加细节的查询所有标签
     * @return result对象
     */
    Result finAllDetail();
    
    Result findDetailById(Long id);
}
