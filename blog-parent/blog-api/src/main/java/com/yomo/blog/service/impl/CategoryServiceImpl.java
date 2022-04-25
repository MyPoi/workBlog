package com.yomo.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yomo.blog.dao.mapper.CategoryMapper;
import com.yomo.blog.dao.pojo.Category;
import com.yomo.blog.service.CategoryService;
import com.yomo.blog.vo.CategoryVo;
import com.yomo.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类别业务实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long categoryId) {

        Category category = categoryMapper.selectById(categoryId);
        return copy(category);
        
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return Result.success(copyList(categories));
    }

    @Override
    public Result findAllDetail() {
        return Result.success(copyList(categoryMapper.selectList(null)));
    }

    @Override
    public Result categoryDetailById(Long id) {
        Category category = categoryMapper.selectById(id);
        return Result.success(copy(category));
    }

    private List<CategoryVo> copyList(List<Category> categories) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        categories.forEach(category -> categoryVoList.add(copy(category)));
        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtil.copyProperties(category,categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
}
