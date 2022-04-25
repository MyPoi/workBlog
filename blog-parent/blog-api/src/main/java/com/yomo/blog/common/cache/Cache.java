package com.yomo.blog.common.cache;

import java.lang.annotation.*;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    
    long expire() default 60 * 1000;
    // 缓存标识 key
    String name() default "";
    
}
