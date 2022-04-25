package com.yomo.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@AllArgsConstructor
public class Result {
    
    private boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    
    public static Result success(Object data){
        return new Result(true,200, "success", data);
    }
    
    public static Result fail(int code, String msg) {
        return  new Result(false, code, msg, null);
    }
}
