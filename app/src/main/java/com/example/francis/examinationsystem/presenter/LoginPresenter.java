package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ILoginView;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.login.LoginByDB;
import com.example.francis.examinationsystem.model.login.LoginByHttpModel;
import com.example.francis.examinationsystem.model.user.UserModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;
import com.example.francis.examinationsystem.util.sp.ProjectSPUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017-3-10.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private LoginByDB loginByDB;
    private LoginByHttpModel loginByHttpModel;
    private UserModel userModel;
    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        loginByDB = new LoginByDB();
        loginByHttpModel = new LoginByHttpModel();
        userModel = new UserModel();
    }

    /**
     * 登录
     *
     * @param name 用户名
     * @param psw  密码
     */
    public void login(String name, String psw) {
        if (TextUtils.isEmpty(name)) {
            getView().showToast("用户名不能为空!");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            getView().showToast("密码不能为空!");
            return;
        }
        getView().showLoading();
        userModel.login(name, psw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        getView().hideLoading();
                        if (user != null) {
                            App.mUser = user;
                            getView().loginSuccess();
                            ProjectSPUtils.setIsLogin(true);
                            ProjectSPUtils.setLoginUser(user);
                        } else {
                            getView().showToast("账号或密码错误");
                        }
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                        ProjectSPUtils.setIsLogin(false);

                    }
                });

    }

    public void register(final String account, String psw, String name, String school,int type) {
        User user = new User();
        user.setUserAccount(account);
        user.setUserPsw(psw);
        user.setUserName(name);
        user.setSchool(school);
        user.setType(type);
        getView().showLoading();
        userModel.register(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {

                        getView().registerSuccess(account);
                        getView().hideLoading();
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }


    public void judgeLoginState(){
        if (ProjectSPUtils.getIsLogin(false)){
            getView().login(ProjectSPUtils.getLoginUser());
        }
    }
}
