package com.yomo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yomo.admin.mapper.AdminMapper;
import com.yomo.admin.pojo.Admin;
import com.yomo.admin.pojo.Permission;
import com.yomo.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    public Admin findAdminByUsername(String username){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username).last("limit 1");
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Permission> findPermissionByAdmin(Long adminId) {
        return adminMapper.findPermissionByAdminId(adminId);
    }
}
