package com.example.francis.examinationsystem.util;

import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.ICallBack;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * GreenDao工具类
 * Created by Francis on 2017-3-13.
 */

public class DaoUtil<T>{
    public void insert(final Class sendClazz, final Object object, final ICallBack<T> iCallBack){
        if (App.getDaoSession().getDao(sendClazz)!=null){
            Observable.create(new Observable.OnSubscribe<T>() {
                @Override
                public void call(Subscriber<? super T> subscriber) {
                    try {
                        App.getDaoSession().getDao(sendClazz).insert(object);
                        subscriber.onNext((T)object);
                    }catch (Exception e){
                        subscriber.onError(e);
                    }
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<T>() {
                        @Override
                        public void call(T t) {
                            iCallBack.onSuccess(t);
                        }
                    }
                    , new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            iCallBack.onFail(throwable.getMessage());
                        }
                    });
        }
    }
}
