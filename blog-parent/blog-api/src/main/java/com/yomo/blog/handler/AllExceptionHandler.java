package com.yomo.blog.handler;

import com.yomo.blog.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.yomo.blog.utils.ErrorCode.SYSTEM_ERROR;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/12
 */
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.failure(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg());
    }

}
