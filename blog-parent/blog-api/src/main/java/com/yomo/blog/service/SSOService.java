package com.yomo.blog.service;

import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.SSOParam;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/13
 */
public interface SSOService {
    /**
     * 登录功能
     * @param SSOParam 登录功能的参数
     * @return Result对象
     */
    Result login(SSOParam SSOParam);

    /**
     * 退出登录
     * @param token 前端返回的token信息
     * @return Result对象
     */
    Result logout(String token);

    /**
     * 注册功能
     * @param ssoParam 注册功能的参数
     * @return Result对象
     */
    Result register(SSOParam ssoParam);
}
