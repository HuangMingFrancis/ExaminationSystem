package com.example.francis.examinationsystem.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IMainView;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.ClassRoomPresenter;
import com.example.francis.examinationsystem.presenter.MainPresent;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.fragment.ClassRoomFragment;
import com.example.francis.examinationsystem.view.fragment.ContactsFragment;
import com.example.francis.examinationsystem.view.fragment.MessageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MVPBaseActivity<IMainView, MainPresent> implements IMainView{

    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.fl_main_container)
    FrameLayout flMainContainer;
    @BindView(R.id.img_main_userIcon)
    ImageView imgMainUserIcon;
    @BindView(R.id.tv_main_userName)
    TextView tvMainUserName;
    @BindView(R.id.img_main_exitLogin)
    ImageView imgMainExitLogin;
    @BindView(R.id.tv_main_drawerCourse)
    TextView tvMainDrawerCourse;
    @BindView(R.id.tv_main_drawerMessage)
    TextView tvMainDrawerMessage;
    @BindView(R.id.tv_main_contacts)
    TextView tvMainContacts;
    @BindView(R.id.fl_main_drawerContent)
    FrameLayout flMainDrawerContent;
    @BindView(R.id.drawer_main_container)
    DrawerLayout drawerMainContainer;


    //保存点击的时间
    private long exitTime;
    //actionbar开关对象
    private ActionBarDrawerToggle mDrawerToggle;
    private ClassRoomFragment mMainCourseFragment;
    private ContactsFragment mContactsFragment;
    private MessageFragment mMessageFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initDrawerToggle();
        mMainCourseFragment = new ClassRoomFragment();
        mContactsFragment = new ContactsFragment();
        mMessageFragment = new MessageFragment();
    }

    @Override
    protected void initView() {
        initToolbar();
        initDrawerContent();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_container, new ClassRoomFragment()).commit();
    }

    private void initDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle((Activity) mContext, drawerMainContainer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }


            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                supportInvalidateOptionsMenu();

            }
        };

        drawerMainContainer.setDrawerListener(mDrawerToggle);
    }



    private void initDrawerContent() {
        tvMainDrawerCourse.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
        if (App.mUser!=null){
            tvMainUserName.setText(App.mUser.getUserAccount());
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("课堂");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected MainPresent createPresenter() {
        return new MainPresent();
    }


    @OnClick({R.id.img_main_userIcon, R.id.img_main_exitLogin, R.id.tv_main_drawerCourse, R.id.tv_main_drawerMessage, R.id.tv_main_contacts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_main_userIcon:
                to(AccountActivity.class,new Intent());
                break;
            case R.id.img_main_exitLogin:
                showExitDialog();
                break;
            case R.id.tv_main_drawerCourse:
                selectNevigationText(Constants.MainDrawerType.COURSE);
                drawerMainContainer.closeDrawer(Gravity.LEFT);
                break;
            case R.id.tv_main_drawerMessage:
                selectNevigationText(Constants.MainDrawerType.MESSAGE);
                drawerMainContainer.closeDrawer(Gravity.LEFT);
                break;
            case R.id.tv_main_contacts:
                selectNevigationText(Constants.MainDrawerType.CONTACTS);
                drawerMainContainer.closeDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && drawerMainContainer.isDrawerOpen(Gravity.LEFT)) {
            drawerMainContainer.closeDrawer(Gravity.LEFT);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出");
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void showExitDialog() {
        new AlertDialog.Builder(mContext).setTitle("退出登录").setMessage("是否确认退出登录").setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        }).create().show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            drawerMainContainer.post(new Runnable() {
                @Override
                public void run() {
                    supportInvalidateOptionsMenu();
                }
            });
            return true;
        }

        switch (item.getItemId()) {
            case R.id.search:
                to(SearchActivity.class,new Intent());
                break;


        }
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void selectNevigationText(int type) {
        if (type == Constants.MainDrawerType.COURSE) {
            tvMainDrawerCourse.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            tvMainDrawerMessage.setBackgroundColor(getResources().getColor(android.R.color.white));
            tvMainContacts.setBackgroundColor(getResources().getColor(android.R.color.white));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mMainCourseFragment).commit();
            getSupportActionBar().setTitle("课程");
        } else if (type == Constants.MainDrawerType.CONTACTS) {
            tvMainDrawerCourse.setBackgroundColor(getResources().getColor(R.color.white));
            tvMainDrawerMessage.setBackgroundColor(getResources().getColor(android.R.color.white));
            tvMainContacts.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mContactsFragment).commit();
            getSupportActionBar().setTitle("通讯录");

        } else {
            tvMainDrawerMessage.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            tvMainContacts.setBackgroundColor(getResources().getColor(android.R.color.white));
            tvMainDrawerCourse.setBackgroundColor(getResources().getColor(android.R.color.white));
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_container, mMessageFragment).commit();
            getSupportActionBar().setTitle("私信");

        }
    }
}
