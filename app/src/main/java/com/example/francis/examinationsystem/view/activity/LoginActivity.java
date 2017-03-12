package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ILoginView;
import com.example.francis.examinationsystem.presenter.LoginPresenter;
import com.example.francis.examinationsystem.util.Toaster;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017-3-10.
 * 登录界面
 */

public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_psw)
    EditText etLoginPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this,MainActivity.class));
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

    @OnClick(R.id.btn_login)
    public void onClick() {
        mPresenter.login(etLoginName.getText().toString(),etLoginPsw.getText().toString());
    }
}
