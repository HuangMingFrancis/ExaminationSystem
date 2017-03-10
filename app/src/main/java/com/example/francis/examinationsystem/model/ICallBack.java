package com.example.francis.examinationsystem.model;

/**
 * Created by Quinn on 2017/3/2.
 */

public interface ICallBack<T> {

    void onSuccess(T t);

    void onFail(String msg);

    void  onError(String code, String msg);

}
