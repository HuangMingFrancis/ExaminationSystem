package com.example.francis.examinationsystem.model.user;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;
import rx.Observable;
import rx.Subscriber;
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
                        if (userDataResult.results.size() > 0) {
                            return Observable.just(userDataResult.results.get(0));
                        } else {
                            return Observable.just(null);
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }


    public Observable<User> updatePsw(String newPsw) {
        User user = new User();
        user.setUserPsw(newPsw);
        return userService.updatePsw(App.mUser.getObjectId(), user)
                .subscribeOn(Schedulers.io());
    }


    public Observable<String> updateHead(String fileName, final String path) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                File file = new File(path);
                final BmobFile bmobFile = new BmobFile(file);
                bmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            subscriber.onNext(bmobFile.getFileUrl());
                        } else {
                            subscriber.onError(e.getCause());
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

}
