package com.yomo.admin.service;

import com.yomo.admin.model.params.PageParam;
import com.yomo.admin.pojo.Permission;
import com.yomo.admin.vo.Result;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
public interface PermissionService{
    Result listPermission(PageParam pageParam);
    
    Result delete(Long id);

    Result add(Permission permission);

    Result update(Permission permission);
}
