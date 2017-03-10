package com.example.francis.examinationsystem.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * MVP架构的基础类、所有要实现mvp开发的界面直接继承该类
 * Created by Quinn on 2017/3/3.
 */
public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.decathView();
    }

    protected abstract T createPresenter();
}
