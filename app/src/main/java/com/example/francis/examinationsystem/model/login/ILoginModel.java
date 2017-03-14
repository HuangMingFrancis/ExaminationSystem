package com.example.francis.examinationsystem.model.login;


import com.example.francis.examinationsystem.model.ICallBack;

/**
 * Created by Quinn on 2017/3/2.
 */

public interface ILoginModel {

    void login(String username, String pwd,ICallBack callBack);
}
