package com.example.francis.examinationsystem.base;

import java.lang.ref.WeakReference;

/**
 * Created by Quinn on 2017/3/3.
 */

public class BasePresenter<V> {
    protected WeakReference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void decathView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected V getView(){
        return mViewRef.get();
    }


    /**
     * 检查是否可执行界面刷新
     */
    protected boolean checkView(){
        if (mViewRef.get()==null) {
           return false;
        }
        return true;
    }
}
