package com.yomo.admin.service.impl;

import com.yomo.admin.pojo.Admin;
import com.yomo.admin.service.AdminService;
import com.yomo.admin.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@Service
public class SecurityUserServiceImpl implements SecurityUserService, UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 登录的时候会把username传递到这里
        // 通过username查询admin表，如果admin存在将密码告诉spring sercurity
        // 如果不存在，返回null 认证失败了
        Admin admin = this.adminService.findAdminByUsername(username);
        if (admin == null) return null;
        UserDetails userDetails = new User(username, admin.getPassword(), new ArrayList<>());
        return userDetails;
    }
}
