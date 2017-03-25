package com.example.francis.examinationsystem.util.net;

import com.example.francis.examinationsystem.entity.bmob.DataResult;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by wzn on 2017/3/25.
 */

public class DataCompose<T> implements Func1<DataResult<T>, Observable<T>> {
    @Override
    public Observable<T> call(DataResult<T> dataResult) {
        if (dataResult.results.size() > 0) {
            return Observable.just(dataResult.results.get(0));
        } else {
            return Observable.just(null);
        }
    }
}
