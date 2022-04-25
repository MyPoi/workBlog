package com.yomo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yomo.blog.dao.mapper.TagMapper;
import com.yomo.blog.dao.pojo.Tag;
import com.yomo.blog.service.TagService;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 标签业务实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/7
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }

    public List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        System.out.println(articleId);
        // mybatis-plus是无法进行多表查询的
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        tags.forEach(System.out::println);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        /**
         * 查询热门标签
         * 1、标签所拥有的数量最多的，就是热门标签
         * 2、查询根据最热tag_id进行分组计数，从大到小排序，取前limit个
         */
        List<Long> tagIds = tagMapper.findHotTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)) {
            // 没有热门标签,返回空值
            return Result.success(Collections.emptyList());
        }
        // 需求的是tagId 和 tagName tag对象
        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result finAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tagList = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result finAllDetail() {
        return Result.success(copyList(tagMapper.selectList(null)));
    }

    @Override
    public Result findDetailById(Long id) {
        return Result.success(copy(tagMapper.selectById(id)));
    }

}
