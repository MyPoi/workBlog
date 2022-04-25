package com.yomo.blog.utils;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
public enum ErrorCode {
    
    PARAMS_ERROR(10001, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003,"token不合法"),
    ACCOUNT_EXIST(10004,"账号已经存在"),
    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),
    UPDATE_ERROR(20001,"上传失败"),
    SYSTEM_ERROR(-999,"系统异常");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public ErrorCode setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorCode setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
