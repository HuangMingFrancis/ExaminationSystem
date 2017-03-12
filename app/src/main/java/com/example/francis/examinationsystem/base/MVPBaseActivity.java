package com.example.francis.examinationsystem.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * MVP架构的基础类、所有要实现mvp开发的界面直接继承该类
 * Created by Quinn on 2017/3/3.
 */
public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( getLayout());
        init();
        initData();
        initView();
        loadData();

    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void loadData();

    private void init() {
        mContext =this;
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.decathView();
    }



    /**
     * 设置默认的toolbar
     * @param toolBar
     * @param title
     */
    protected void setToolBar(Toolbar toolBar, String title){
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(title);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void to(@NonNull final Class clazz, @NonNull final Intent intent){
        intent.setClass(this,clazz);
        startActivity(intent);
    }
    public void toForResult(@NonNull final Class clazz, @NonNull final Intent intent,@NonNull int requestCode){
        intent.setClass(this,clazz);
        startActivityForResult(intent,requestCode);
    }

    protected abstract T createPresenter();
}
