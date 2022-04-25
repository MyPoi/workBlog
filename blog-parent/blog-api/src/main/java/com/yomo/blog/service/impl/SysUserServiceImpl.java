package com.yomo.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yomo.blog.dao.mapper.SysUserMapper;
import com.yomo.blog.dao.pojo.SysUser;
import com.yomo.blog.service.SysUserService;
import com.yomo.blog.service.TokenService;
import com.yomo.blog.vo.LoginUserVo;
import com.yomo.blog.vo.Result;
import com.yomo.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yomo.blog.utils.ErrorCode.TOKEN_ERROR;

/**
 * <p>
 * 系统用户业务实现类
 * </p>
 *
 * @author yomo
 * @since 2022/4/7
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 根据id查找用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        return sysUser == null ? new SysUser().setNickname("yomo") : sysUser;
    }

    @Override
    public UserVo findUserVoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser = sysUser == null ? new SysUser()
                .setId(1L)
                .setNickname("yomo")
                .setAvatar("/static/img/logo.b3a48c0.png") : sysUser;
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(sysUser, userVo);
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }

    /**
     * 根据用户名密码查询用户信息
     *
     * @param account  用户名
     * @param password 密码
     * @return SysUser对象
     */
    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account).eq(SysUser::getPassword, password)
                .select(SysUser::getId,SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname)
                .last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 根据token查询用户信息
     *
     * @param token 前端返回的token
     * @return SysUser对象
     */
    @Override
    public Result findUserByToken(String token) {
        SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) return Result.failure(TOKEN_ERROR.getCode(), TOKEN_ERROR.getMsg());
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(String.valueOf(sysUser.getId()))
                .setNickname(sysUser.getNickname())
                .setAvatar(sysUser.getAvatar())
                .setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account).last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        // 保存用户这 id会自动生成 默认是分布式id 采用雪花算法
        // mybatis-plus默认就是雪花算法id
        this.sysUserMapper.insert(sysUser);
    }
}
