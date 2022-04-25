package com.yomo.blog.common.cache;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.yomo.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

import static com.yomo.blog.utils.ErrorCode.SYSTEM_ERROR;

/**
 * <p>
 * 缓存aop切面：定义了切点与通知的关系
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.yomo.blog.common.cache.Cache)")
    public void pt() {
    }

    /**
     * 通知
     *
     * @param joinPoint
     * @return
     */
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            // 类名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            // 方法名
            String methodName = signature.getName();
            // 获取参数
            Object[] args = joinPoint.getArgs();
            // 参数类型
            Class[] parameterType = new Class[args.length];

            String params = "";
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    // 如果参数有值
                    params += JSONUtil.toJsonStr(args[i]);
                    parameterType[i] = args[i].getClass();
                } else {
                    parameterType[i] = null;
                }
            }
            if (StrUtil.isNotEmpty(params)) {
                // 加密 以防出现key过长以及字符转义获取不到的情况
                params = DigestUtils.md5Hex(params);
            }
            Method method = joinPoint.getSignature().getDeclaringType().getMethod(methodName, parameterType);
            // 获取cache注解
            Cache annotation = method.getAnnotation(Cache.class);
            // 缓存过期时间
            long expire = annotation.expire();
            // 缓存名称
            String name = annotation.name();
            // 先从redis获取
            String key = name + ":" + className + ":" + methodName + ":" + params;
            String redisValue = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(redisValue)) {
                log.info("走了缓存~~~,{},{}", className, methodName);
                return JSON.parseObject(redisValue, Result.class);
            }
            Object proceed = joinPoint.proceed();
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(proceed), Duration.ofMillis(expire));
            log.info("存入缓存~~~ {},{}", className, methodName);
            return proceed;

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return Result.failure(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg());
    }

}
