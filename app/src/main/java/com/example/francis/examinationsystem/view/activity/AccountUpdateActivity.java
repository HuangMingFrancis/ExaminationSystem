package com.example.francis.examinationsystem.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IAccountUpdateView;
import com.example.francis.examinationsystem.presenter.AccountUpdatePresenter;
import com.example.francis.examinationsystem.util.Toaster;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/18.
 */

public class AccountUpdateActivity extends MVPBaseActivity<IAccountUpdateView, AccountUpdatePresenter> implements IAccountUpdateView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.et_dialog_password_pass)
    EditText etDialogPasswordPass;
    @BindView(R.id.et_dialog_password_new)
    EditText etDialogPasswordNew;
    @BindView(R.id.et_dialog_password_confirm)
    EditText etDialogPasswordConfirm;
    @BindView(R.id.btn_dialog_password_save)
    Button btnDialogPasswordSave;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_account_update;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        initToolbar();
        setToolBar(toolbarMain,"修改密码");
    }

//    private void initToolbar() {
//        setSupportActionBar(toolbarMain);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle("修改密码");
//    }

    @Override
    protected void loadData() {

    }

    @Override
    protected AccountUpdatePresenter createPresenter() {
        return new AccountUpdatePresenter();
    }

    @OnClick(R.id.btn_dialog_password_save)
    public void onClick() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
