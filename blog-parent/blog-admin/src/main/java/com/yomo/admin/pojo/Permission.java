package com.yomo.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
public class Permission {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String path;
    private String description;

    public Long getId() {
        return id;
    }

    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Permission setPath(String path) {
        this.path = path;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
