package com.yomo.blog.utils;

import com.yomo.blog.dao.pojo.SysUser;

/**
 * <p>
 * 线程变量隔离
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
public class UserThreadLocal {
    
    private UserThreadLocal(){}
    
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();
    
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    
    public static SysUser get(){
        return LOCAL.get();
    }
    
    public static void remove(){
        LOCAL.remove();
    }
    
}
