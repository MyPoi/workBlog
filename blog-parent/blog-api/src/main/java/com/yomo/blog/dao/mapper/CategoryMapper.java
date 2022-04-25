package com.yomo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yomo.blog.dao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 类别的mapper层
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
