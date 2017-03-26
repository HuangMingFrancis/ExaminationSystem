package com.example.francis.examinationsystem.base;

import android.content.Context;

/**
 * Created by wzn on 2017/3/25.
 */

public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    protected T mPresenter;
    protected Context mContext;

    @Override
    protected void initVarious() {
        super.initVarious();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        mContext = getActivity();
    }

    protected abstract T createPresenter();
}
