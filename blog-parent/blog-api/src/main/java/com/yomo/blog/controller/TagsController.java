package com.yomo.blog.controller;

import com.yomo.blog.service.TagService;
import com.yomo.blog.vo.Result;
import io.lettuce.core.RedisConnectionStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author yomo
 * @since 2022/4/11
 */
@RestController
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    @RequestMapping("hot")
    public Result hot() {
        int limit = 6;
        return tagService.hots(limit);
    }
    
    @GetMapping
    public Result findAll(){
        return tagService.finAll();
    }

    @GetMapping("/detail")
    public Result findAllDetail(){
        return tagService.finAllDetail();
    }
    
    @GetMapping("/detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }

}
