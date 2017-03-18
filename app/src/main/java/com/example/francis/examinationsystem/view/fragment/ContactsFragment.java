package com.example.francis.examinationsystem.view.fragment;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BasePresenterFragment;
import com.example.francis.examinationsystem.contract.IConstactsView;
import com.example.francis.examinationsystem.presenter.ConstactsPresenter;

/**
 * Created by Francis on 2017/3/18.
 */

public class ContactsFragment extends BasePresenterFragment<IConstactsView,ConstactsPresenter> implements IConstactsView {
    @Override
    protected ConstactsPresenter createPresenter() {
        return new ConstactsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
