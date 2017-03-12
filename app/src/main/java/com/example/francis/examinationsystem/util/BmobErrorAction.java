package com.example.francis.examinationsystem.util;


import com.example.francis.examinationsystem.entity.BmobErrorData;

import rx.functions.Action1;

/**
 * bmob网络请求错误处理Action
 * Created by wzn on 2017/1/14.
 */

public abstract class BmobErrorAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        BmobErrorData errorData = new BmobErrorData();
        errorData = errorData.handleError(throwable);
        call(errorData.getError());
    }

    public abstract void call(String msg);

}

