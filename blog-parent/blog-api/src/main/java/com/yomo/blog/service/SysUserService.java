package com.yomo.blog.service;

import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.UserVo;

/**
 * <p>
 * 系统用户业务接口
 * </p>
 *
 * @author yomo
 * @since 2022/4/7
 */
public interface SysUserService {
    
    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return SysUser对象
     */
    SysUser findUserById(Long id);

    /**
     * 根据用户名密码查询用户信息
     * @param account 用户名
     * @param password 密码
     * @return SysUser对象
     */
    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token 前端返回的token
     * @return SysUser对象
     */
    Result findUserByToken(String token);

    /**
     * 根据用户名查找用户
     * @param account 用户名
     * @return SysUser对象
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser 用户对象
     */
    void save(SysUser sysUser);

    /**
     * 根据作者id获取userVo对象
     * @param authorId 作者id
     * @return userVo对象
     */
    UserVo findUserVoById(Long authorId);
}
