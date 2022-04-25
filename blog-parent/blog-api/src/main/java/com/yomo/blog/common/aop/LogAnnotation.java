package com.yomo.blog.common.aop;

import java.lang.annotation.*;

/**
 * <p>
 * aop日志注解
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
// TYPE：代表可以放在类上，Method代表可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    
    String module() default "";
    
    String operator() default "";
    
}
