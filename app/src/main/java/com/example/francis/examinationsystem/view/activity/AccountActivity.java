package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IAccountView;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.AccountPresenter;
import com.example.francis.examinationsystem.util.ImageLoaderUtils;
import com.example.francis.examinationsystem.util.IntentUtils;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.thirty.ActionSheetDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.francis.examinationsystem.R.id.img_account_user;

/**
 * Created by Francis on 2017/3/18.
 */

public class AccountActivity extends MVPBaseActivity<IAccountView, AccountPresenter> implements IAccountView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(img_account_user)
    ImageView imgAccountUser;
    @BindView(R.id.ll_account_userIcon)
    RelativeLayout llAccountUserIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_account_name)
    RelativeLayout llAccountName;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.ll_account_school)
    RelativeLayout llAccountSchool;
    @BindView(R.id.ll_account_password)
    RelativeLayout llAccountPassword;


    private String account;


    private String File_Path;

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
        return R.layout.activity_accout;
    }

    @Override
    protected void initData() {
        if (App.mUser!=null){
            tvAccount.setText(App.mUser.getUserAccount());
            tvName.setText(App.mUser.getUserName());
            tvSchool.setText(App.mUser.getSchool());
            account=App.mUser.getUserAccount();
        }
    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain,"个人信息");
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
    }


    @OnClick({img_account_user, R.id.ll_account_userIcon, R.id.tv_school, R.id.ll_account_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case img_account_user:
                showUpdateHeadDialog("选择图片", "拍照", "从相册中选择");
                break;
            case R.id.ll_account_userIcon:
                break;
            case R.id.tv_school:
                break;
            case R.id.ll_account_password:
                to(AccountUpdateActivity.class,new Intent());
                break;
        }
    }


    private void showUpdateHeadDialog(String title, String photo, String album) {
        new ActionSheetDialog(mContext)
                .builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem(photo, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        File file = new File(Constants.Fold.PHOTO_FOLDER);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File_Path = IntentUtils.openCamera(AccountActivity.this);
                    }
                })
                .addSheetItem(album, ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        IntentUtils.openImageFile(AccountActivity.this);
                    }
                })
                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IntentUtils.OPEN_IMGAE && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            Bitmap bitmap = null;
            try {
                FileOutputStream fos = new FileOutputStream(Constants.Fold.PHOTO_FOLDER + account + "logo.jpg", false);
                InputStream is = mContext.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(is);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageLoaderUtils.displayNoDisk(mContext, imgAccountUser, Constants.Fold.PHOTO_FOLDER + account + "logo.jpg");
            File file = new File(Constants.Fold.PHOTO_FOLDER + account + "logo.jpg");
            mPresenter.updataeUserHead(account+"logo.jpg",file,Constants.Fold.PHOTO_FOLDER + account + "logo.jpg");
        }
        if (requestCode == IntentUtils.CAMERA_REQUEST && resultCode == RESULT_OK) {
            File file = new File(File_Path);
            ImageLoaderUtils.display(mContext, imgAccountUser, file.getPath());
            mPresenter.updataeUserHead(account + "logo.jpg",file,file.getPath());
        }
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
