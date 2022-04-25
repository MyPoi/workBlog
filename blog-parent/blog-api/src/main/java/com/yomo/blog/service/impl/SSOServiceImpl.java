package com.yomo.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.SSOService;
import com.yomo.blog.service.SysUserService;
import com.yomo.blog.utils.JWTUtils;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.param.SSOParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.yomo.blog.utils.Constants.*;
import static com.yomo.blog.utils.ErrorCode.*;

/**
 * <p>
 * SSO功能模块
 * </p>
 *
 * @author yomo
 * @since 2022/4/13
 */
@Service
@Transactional
public class SSOServiceImpl implements SSOService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(SSOParam ssoParam) {

        String account = ssoParam.getAccount();
        String password = ssoParam.getPassword();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password))
            return Result.failure(PARAMS_ERROR.getCode(), PARAMS_ERROR.getMsg());

        // password 加密
        password = DigestUtils.md5Hex(password + SALT);

        // 从数据库中查找用户
        SysUser sysUser = sysUserService.findUser(account, password);
        if (sysUser == null)
            return Result.failure(ACCOUNT_PWD_NOT_EXIST.getCode(), ACCOUNT_PWD_NOT_EXIST.getMsg());

        // 保存在缓存中
        String token = JWTUtils.createToken(sysUser.getId());
        stringRedisTemplate.opsForValue().set(LOGIN_TOKEN_KEY + token, JSONUtil.toJsonStr(sysUser), LOGIN_TOKEN_TTL, TimeUnit.DAYS);

        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        stringRedisTemplate.delete(LOGIN_TOKEN_KEY + token);
        return Result.success(null);
    }

    @Override
    public Result register(SSOParam ssoParam) {
        /**
         * 1、判断参数是否合法
         * 2、判断账户是否存在，存在，返回账户已经被注册
         * 3、不存在，注册账户
         * 4、生成token
         * 5、存入redis，并返回
         * 6、注意加上事务，一旦中间的任何过程出现问题，注册的用户需要回滚
         */
        String account = ssoParam.getAccount();
        String password = ssoParam.getPassword();
        String nickname = ssoParam.getNickname();
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password) || StrUtil.isBlank(nickname))
            return Result.failure(PARAMS_ERROR.getCode(), PARAMS_ERROR.getMsg());
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null) Result.failure(ACCOUNT_EXIST.getCode(), ACCOUNT_EXIST.getMsg());
        sysUser = new SysUser();
        sysUser.setNickname(nickname)
                .setAccount(account)
                .setPassword(DigestUtils.md5Hex(password + SALT))
                .setCreateDate(System.currentTimeMillis())
                .setLastLogin(System.currentTimeMillis())
                .setAvatar("/static/img/logo.b3a48c0.png")
                .setAdmin(1)
                .setDeleted(0)
                .setSalt("")
                .setStatus("")
                .setEmail("");
        this.sysUserService.save(sysUser);
        String token = JWTUtils.createToken(sysUser.getId());
        stringRedisTemplate.opsForValue().set(LOGIN_TOKEN_KEY + token, JSONUtil.toJsonStr(sysUser), LOGIN_TOKEN_TTL, TimeUnit.DAYS);
        return Result.success(token);
    }
}
