package com.yomo.admin.service;

import com.yomo.admin.pojo.Admin;
import com.yomo.admin.pojo.Permission;

import java.util.List;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
public interface AdminService {
    Admin findAdminByUsername(String username);

    List<Permission> findPermissionByAdmin(Long adminId);
}
