package com.zxyoyo.apk.ji.login.source;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.UUID;

/**
 * 用户信息实体类
 */
@Entity
public class UserBean {
    @Id(autoincrement = true)
    private long id;
    private String userKey;
    private String loginName;
    private String password;
    private String lastLoginTime;
    @Generated(hash = 1107061644)
    public UserBean(long id, String userKey, String loginName, String password,
            String lastLoginTime) {
        this.id = id;
        this.userKey = userKey;
        this.loginName = loginName;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getUserKey() {
        return this.userKey;
    }
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
