package com.yomo.blog.dao.pojo;

import lombok.Data;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/18
 */
@Data
public class Category {
    
    private Long id;
    private String avatar;
    private String categoryName;
    private String description;
    
}
