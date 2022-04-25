package com.yomo.blog.service;

import com.yomo.blog.dao.pojo.SysUser;

/**
 * <p>
 * Token业务类
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
public interface TokenService {
    SysUser checkToken(String token);
}
