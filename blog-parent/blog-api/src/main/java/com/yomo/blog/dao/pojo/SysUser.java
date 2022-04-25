package com.yomo.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/6
 */
public class SysUser {
    
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;

    public Long getId() {
        return id;
    }

    public SysUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public SysUser setAccount(String account) {
        this.account = account;
        return this;
    }

    public Integer getAdmin() {
        return admin;
    }

    public SysUser setAdmin(Integer admin) {
        this.admin = admin;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public SysUser setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public SysUser setCreateDate(Long createDate) {
        this.createDate = createDate;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public SysUser setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public SysUser setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public SysUser setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public SysUser setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public SysUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public SysUser setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", admin=" + admin +
                ", avatar='" + avatar + '\'' +
                ", createDate=" + createDate +
                ", deleted=" + deleted +
                ", email='" + email + '\'' +
                ", lastLogin=" + lastLogin +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

