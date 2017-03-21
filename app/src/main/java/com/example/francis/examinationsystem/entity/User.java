package com.example.francis.examinationsystem.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import cn.bmob.v3.BmobObject;

/**
 * Created by Francis on 2017-3-10.
 */

@Entity
public class User extends BaseObject {
    @Id
    private String userAccount;//账号
    private String userPsw;//密码
    private String userName;//名字
    private String userHead;//头像
    private String userEmail;//邮箱
    private String school;//学校
    @Generated(hash = 930240023)
    public User(String userAccount, String userPsw, String userName,
            String userHead, String userEmail, String school) {
        this.userAccount = userAccount;
        this.userPsw = userPsw;
        this.userName = userName;
        this.userHead = userHead;
        this.userEmail = userEmail;
        this.school = school;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getUserAccount() {
        return this.userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getUserPsw() {
        return this.userPsw;
    }
    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserHead() {
        return this.userHead;
    }
    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }
    public String getUserEmail() {
        return this.userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }


}
