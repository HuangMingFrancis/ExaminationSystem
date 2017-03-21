package com.example.francis.examinationsystem.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import cn.bmob.v3.BmobObject;

/**
 * Created by Francis on 2017-3-10.
 */
@Entity
public class User extends BmobObject {
    @Id
    private Long id;
    
    private String userAccount;//账号
    private String userPsw;//密码
    private String userName;//名字
    private String userHead;//头像
    private String userEmail;//邮箱

    @Generated(hash = 629845616)
    public User(Long id, String userAccount, String userPsw, String userName,
            String userHead, String userEmail) {
        this.id = id;
        this.userAccount = userAccount;
        this.userPsw = userPsw;
        this.userName = userName;
        this.userHead = userHead;
        this.userEmail = userEmail;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
