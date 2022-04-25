package com.yomo.blog.common.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.yomo.blog.utils.HttpContextUtils;
import com.yomo.blog.utils.IpUtils;
import com.yomo.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/21
 */
@Component
@Aspect // 切面 定义了通知和切面的关系
@Slf4j
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.yomo.blog.common.aop.LogAnnotation)")
    public void pt(){
        
    }

    /**
     * 环绕通知
     * @return Object类
     */
    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 执行时间
        Long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        recordLog(joinPoint,time);
        return result;
    }
    
    private void recordLog(ProceedingJoinPoint joinPoint, Long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSONUtil.toJsonStr(args[0]);
        log.info("params:{}",params);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpUtils.getIP(request));


        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");
    }
    
}
