package com.yomo.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yomo.admin.pojo.Admin;
import com.yomo.admin.pojo.Permission;
import com.yomo.admin.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
public class AuthService {
    
    @Autowired
    private AdminService adminService;
    
    public boolean auth(HttpServletRequest request, Authentication authentication){
        // 权限认证
        // 请求路径
        String requestURI = request.getRequestURI();
        // userdetail信息
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal))
            return false; // 未登录
        UserDetails userDetails = (UserDetails) principal;
        // 得到用户名
        String username = userDetails.getUsername();
        // 根据用户名查找用户信息
        Admin admin = adminService.findAdminByUsername(username);
        if (admin == null) return false;
        if (1 == admin.getId()){
            // 超级管理员
            return true;
        }
        // 得到用户信息
        Long id = admin.getId();
        List<Permission> permissionList = this.adminService.findPermissionByAdmin(id);
        requestURI = StringUtils.split(requestURI,"?")[0];
        for (Permission permission : permissionList) {
            if (requestURI.equals(permission.getPath()))
                return true;
        }
        
        return false;
    }
}
