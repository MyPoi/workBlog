package com.yomo.blog.service;

import com.yomo.blog.vo.CategoryVo;
import com.yomo.blog.vo.Result;

import java.util.List;

/**
 * <p>
 * 类别业务
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
public interface CategoryService {
    /**
     * 根据ID查询分类
     * @param categoryId 分类id
     * @return 类别视图对象
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询所有的类别
     * @return result对象
     */
    Result findAll();

    /**
     * 查询所有类别的细节
     * @return result对象
     */
    Result findAllDetail();

    Result categoryDetailById(Long id);
}
