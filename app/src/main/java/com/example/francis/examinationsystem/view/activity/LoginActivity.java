package com.example.francis.examinationsystem.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ILoginView;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.presenter.LoginPresenter;
import com.example.francis.examinationsystem.util.Toaster;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017-3-10.
 * 登录界面
 */

public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {


    @BindView(R.id.img_login_logo)
    ImageView imgLoginLogo;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    private AlertDialog RegisterDialog;

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
        mPresenter.judgeLoginState();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void registerSuccess(String userName) {
        etLoginName.setText(userName);
        RegisterDialog.hide();
    }

    @Override
    public void login(User user) {
        etLoginName.setText(user.getUserName());
        etLoginPassword.setText(user.getUserPsw());
        mPresenter.login(user.getUserName(),user.getUserPsw());
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


    @OnClick({R.id.btn_login_login, R.id.tv_login_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                mPresenter.login(etLoginName.getText().toString(), etLoginPassword.getText().toString());
                break;
            case R.id.tv_login_register:
                showRegisterDialog();
                break;
        }
    }

    private void showRegisterDialog() {
        RegisterDialog = new AlertDialog.Builder(mContext).create();
        RegisterDialog.setCanceledOnTouchOutside(false);
        View view;

        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_register_teacher, null);

        final EditText et_register_account = (EditText) view.findViewById(R.id.et_register_account);
        final EditText et_register_name = (EditText) view.findViewById(R.id.et_register_name);
        final EditText et_register_password = (EditText) view.findViewById(R.id.et_register_password);
        final EditText et_register_school = (EditText) view.findViewById(R.id.et_register_school);
        final Spinner spChooseType= (Spinner) view.findViewById(R.id.spChooseType);
        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDialog.dismiss();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_register_account.getText().toString().equals("") &&
                        !et_register_password.getText().toString().equals("") &&
                        !et_register_school.getText().toString().equals("") &&
                        !et_register_name.getText().toString().equals("")) {
                    mPresenter.register(et_register_account.getText().toString(), et_register_password.getText().toString()
                            , et_register_name.getText().toString(), et_register_school.getText().toString(),spChooseType.getSelectedItem().toString().equals("老师")?0:1);

                } else {
                    showRegisterFailDialog();
                }

            }
        });


        RegisterDialog.setView(view);
        RegisterDialog.show();

    }

    private void showRegisterFailDialog() {
        new AlertDialog.Builder(mContext).setTitle("注册失败").setMessage("不能为空,请填写")
                .setPositiveButton("确认", null).setNegativeButton("取消", null).create().show();
    }
}
