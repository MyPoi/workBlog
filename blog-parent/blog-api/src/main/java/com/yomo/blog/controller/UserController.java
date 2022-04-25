package com.yomo.blog.controller;

import com.yomo.blog.service.SysUserService;
import com.yomo.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findUserByToken(token);
    }
    
}
