package com.example.francis.examinationsystem.view.fragment;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BasePresenterFragment;
import com.example.francis.examinationsystem.contract.IMessageView;
import com.example.francis.examinationsystem.presenter.MessagePresenter;
import com.example.francis.examinationsystem.util.Toaster;

/**
 * Created by Francis on 2017/3/18.
 */

public class MessageFragment extends BasePresenterFragment<IMessageView,MessagePresenter> implements IMessageView {
    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void showToast(String message) {
        Toaster.showShort(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
