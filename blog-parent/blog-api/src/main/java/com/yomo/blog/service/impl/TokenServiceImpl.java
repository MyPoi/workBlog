package com.yomo.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.TokenService;
import com.yomo.blog.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.yomo.blog.utils.Constants.LOGIN_TOKEN_KEY;

/**
 * <p>
 * Token业务类实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public SysUser checkToken(String token) {
        if (StrUtil.isBlank(token)) return null;
        Map<String, Object> objectMap = JWTUtils.checkToken(token);
        if (objectMap == null) return null;
        String userJson = stringRedisTemplate.opsForValue().get(LOGIN_TOKEN_KEY + token);
        if (StrUtil.isBlank(userJson)) return null;
        return JSONUtil.toBean(userJson, SysUser.class);
    }
}
