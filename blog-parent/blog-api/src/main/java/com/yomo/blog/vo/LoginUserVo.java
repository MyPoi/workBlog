package com.yomo.blog.vo;

import lombok.Data;

/**
 * <p>
 * 登录用户视图对象
 * </p>
 *
 * @author yomo
 * @since 2022/4/14
 */
public class LoginUserVo {
    
    private String id;
    
    private String account;
    
    private String nickname;
    
    private String avatar;

    public String getId() {
        return id;
    }

    public LoginUserVo setId(String id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public LoginUserVo setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public LoginUserVo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public LoginUserVo setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUserVo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
