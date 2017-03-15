package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IFlashView;
import com.example.francis.examinationsystem.presenter.FlashPresenter;
import com.example.francis.examinationsystem.util.Toaster;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Francis on 2017-3-15.
 */

public class FlashActivity extends MVPBaseActivity<IFlashView,FlashPresenter> implements IFlashView{

    @Override
    protected int getLayout() {
        return R.layout.activity_flash;
    }

    @Override
    protected void initData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mPresenter.judgeJumpActivity();
            }
        }, 2000);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected FlashPresenter createPresenter() {
        return new FlashPresenter();
    }

    @Override
    public void goGuideActivity() {
        to(GuideActivity.class,new Intent());
        finish();
    }

    @Override
    public void goMainActivity() {
        to(MainActivity.class,new Intent());
        finish();
    }

    @Override
    public void goLoginActivity() {
        to(LoginActivity.class,new Intent());
        finish();
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
