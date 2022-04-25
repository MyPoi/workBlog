package com.yomo.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 返回信息类
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result failure(int code, String msg) {
        return new Result(false, code, msg, null);
    }
}
