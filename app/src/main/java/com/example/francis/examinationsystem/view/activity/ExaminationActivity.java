package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IExaminationView;
import com.example.francis.examinationsystem.presenter.ExaminationPresenter;
import com.shamanland.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/21.
 */

public class ExaminationActivity extends MVPBaseActivity<IExaminationView, ExaminationPresenter> implements IExaminationView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.list_examination)
    RecyclerView listExamination;
    @BindView(R.id.fresh_examination)
    SwipeRefreshLayout freshExamination;
    @BindView(R.id.btn_course_tab_publish)
    FloatingActionButton btnCourseTabPublish;


    @Override
    public void showToast(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_examination;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain, "考卷");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected ExaminationPresenter createPresenter() {
        return new ExaminationPresenter();
    }

    @OnClick(R.id.btn_course_tab_publish)
    public void onClick() {
        toForResult(AddExaminatinoActivity.class,new Intent(),0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){

        }
        super.onActivityResult(requestCode, resultCode, data);
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
