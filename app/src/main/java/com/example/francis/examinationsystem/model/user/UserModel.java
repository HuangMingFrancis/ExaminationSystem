package com.example.francis.examinationsystem.model.user;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by wzn on 2017/3/21.
 */

public class UserModel {
    UserService userService;

    public UserModel() {
        userService = RetrofitHelper.getRetrofit().create(UserService.class);
    }

    public Observable<User> register(User user) {
        return userService.register(user).subscribeOn(Schedulers.io());
    }


    public Observable<User> login(String loginName, String passWord) {
        User user = new User();
        user.setUserAccount(loginName);
        user.setUserPsw(passWord);
        return userService.login(JSONObject.toJSONString(user)).subscribeOn(Schedulers.io());
    }
}
