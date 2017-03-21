package com.example.francis.examinationsystem.model.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;



import rx.Observable;
import rx.functions.Func1;
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
        return userService.login(JSONObject.toJSONString(user))
                .flatMap(new Func1<DataResult<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(DataResult<User> userDataResult) {
                        if (userDataResult.results.size()>0){
                            return Observable.just(userDataResult.results.get(0));
                        }else {
                            return Observable.just(null);
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
}
