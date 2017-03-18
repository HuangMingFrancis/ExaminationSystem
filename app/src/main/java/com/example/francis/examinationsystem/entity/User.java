package com.example.francis.examinationsystem.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Francis on 2017-3-10.
 */

@Entity
public class User {
    @Id
    private String user_account;//账号
    private String user_psw;//密码
    private String user_name;//名字
    private String user_head;//头像
    private String user_email;//邮箱
    @Generated(hash = 346019161)
    public User(String user_account, String user_psw, String user_name,
            String user_head, String user_email) {
        this.user_account = user_account;
        this.user_psw = user_psw;
        this.user_name = user_name;
        this.user_head = user_head;
        this.user_email = user_email;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getUser_account() {
        return this.user_account;
    }
    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }
    public String getUser_psw() {
        return this.user_psw;
    }
    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_head() {
        return this.user_head;
    }
    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }
    public String getUser_email() {
        return this.user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

}  
