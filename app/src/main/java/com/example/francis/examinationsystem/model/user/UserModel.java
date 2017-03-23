package com.example.francis.examinationsystem.model.user;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;

import org.json.JSONException;

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


    public Observable<User> updatePsw(String newPsw){
        User user=new User();
        user.setUserPsw(newPsw);
        return userService.updatePsw(App.mUser.getObjectId(), user)
                .subscribeOn(Schedulers.io());
    }


    public Observable<String> updateHead(String fileName,byte[] headFile){
        Log.i("http", "updateHead: "+fileName);
        return userService.updateHead("application/x-jpg","https://api.bmob.cn/2/files/"+fileName,headFile)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<org.json.JSONObject, Observable<String>>() {
                    @Override
                    public Observable<String> call(org.json.JSONObject jsonObject) {
                        try {
                            Log.i("http", "call: "+jsonObject.toString());
                            return Observable.just(jsonObject.getString("url"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
    }

}
