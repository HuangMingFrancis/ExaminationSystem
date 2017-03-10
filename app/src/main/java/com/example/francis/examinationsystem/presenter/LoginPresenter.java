package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ILoginView;
import com.example.francis.examinationsystem.model.ICallBack;
import com.example.francis.examinationsystem.model.login.LoginByDB;
import com.example.francis.examinationsystem.model.login.LoginByHttpModel;

/**
 * Created by Francis on 2017-3-10.
 */

public class LoginPresenter extends BasePresenter<ILoginView>{

    private LoginByDB loginByDB;
    private LoginByHttpModel loginByHttpModel;

    public LoginPresenter() {
        loginByDB=new LoginByDB();
        loginByHttpModel=new LoginByHttpModel();
    }

    /**
     * 登录
     * @param name 用户名
     * @param psw 密码
     */
    public void login(String name,String psw){
        if (TextUtils.isEmpty(name)){
            getView().showToast("用户名不能为空!");
            return;
        }
        if (TextUtils.isEmpty(psw)){
            getView().showToast("密码不能为空!");
            return;
        }
        getView().loginSuccess();
        loginByDB.login(name, psw, new ICallBack() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onError(String code, String msg) {

            }
        });

    }
}
