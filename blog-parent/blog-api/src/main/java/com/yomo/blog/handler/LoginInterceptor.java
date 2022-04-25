package com.yomo.blog.handler;

import cn.hutool.json.JSONUtil;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.TokenService;
import com.yomo.blog.utils.UserThreadLocal;
import com.yomo.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yomo.blog.utils.ErrorCode.NO_LOGIN;

/**
 * <p>
 * 登录拦截器
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在执行controller方法之前执行
        /**
         * 1、需要判断 请求的接口路径 是否为handlerMethod（controller方法）
         * 2、判断token是否为空，如果为空，未登录，不为空，登录验证
         * 3、如果认证成功，放行
         */
        // handler可能是访问资源的handler 放行
        if (!(handler instanceof HandlerMethod)) return true;
        String token = request.getHeader("Authorization");

        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSONUtil.toJsonStr(Result.failure(NO_LOGIN.getCode(), NO_LOGIN.getMsg())));
            return false;
        }

        // 登录验证成功，放行
        // 我希望在controller中直接获取用户信息
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不删除会有内存泄露的风险
        UserThreadLocal.remove();
    }
}
