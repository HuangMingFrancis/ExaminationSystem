package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IAccountUpdateView;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.user.UserModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/18.
 */

public class AccountUpdatePresenter extends BasePresenter<IAccountUpdateView> {

    private UserModel userModel;

    public AccountUpdatePresenter() {
        userModel=new UserModel();
    }

    /**
     * 修改密码
     * @param oldPsw
     * @param newPsw
     * @param newPswAgain
     */
    public void updatePsw(String oldPsw,String newPsw,String newPswAgain){
        if (TextUtils.isEmpty(oldPsw)){
            getView().showToast("旧密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(newPsw)){
            getView().showToast("新密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(newPswAgain)){
            getView().showToast("确认密码不能为空！");
            return;
        }
        if (!TextUtils.equals(newPsw,newPswAgain)){
            getView().showToast("两次输入的密码不正确！");
            return;
        }
        if (!TextUtils.equals(oldPsw,App.mUser.getUserPsw())){
            getView().showToast("旧密码输入错误！");
            return;
        }

        userModel.updatePsw(newPsw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        App.mUser=user;
                        getView().showToast("修改成功");
                        getView().updatePswSuccess();
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }
}
