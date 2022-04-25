package com.yomo.blog.controller;

import com.yomo.blog.service.SSOService;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.SSOParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

/**
 * <p>
 * 登录
 * </p>
 *
 * @author yomo
 * @since 2022/4/13
 */
@RestController
public class SSOController {
    
    @Autowired
    private SSOService ssoService;

    /**
     * 登录
     * @param ssoParam sso参数对象
     * @return result对象
     */
    @PostMapping("login")
    public Result login(@RequestBody SSOParam ssoParam){
        // 登录  验证用户 访问用户
        return ssoService.login(ssoParam);
    }

    /**
     * 注册功能
     * @param ssoParam sso参数对象
     * @return result对象
     */
    @PostMapping("register")
    public Result register(@RequestBody SSOParam ssoParam){
        return ssoService.register(ssoParam);
    }

    /**
     * 退出登录
     * @param token 前端传递的token
     * @return result对象
     */
    @GetMapping("logout")
    public Result logout(@RequestHeader("Authorization") String token){
        return ssoService.logout(token);
    }
}
