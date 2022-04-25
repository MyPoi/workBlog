package com.yomo.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yomo.admin.mapper.PermissionMapper;
import com.yomo.admin.model.params.PageParam;
import com.yomo.admin.pojo.Permission;
import com.yomo.admin.service.PermissionService;
import com.yomo.admin.vo.PageResult;
import com.yomo.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result listPermission(PageParam pageParam) {
        /**
         * 要的数据，管理台，表的所有字段，Permission对象
         */
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(pageParam.getQueryString()))
            queryWrapper.eq(Permission::getName,pageParam.getQueryString());
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());
        return Result.success(pageResult);
    }

    @Override
    public Result delete(Long id) {
        this.permissionMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result add(Permission permission) {
        this.permissionMapper.insert(permission);
        return Result.success(null);
    }

    @Override
    public Result update(Permission permission) {
        this.permissionMapper.updateById(permission);
        return Result.success(null);
    }
}
