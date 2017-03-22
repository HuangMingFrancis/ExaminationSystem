package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IExaminationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.presenter.ExaminationPresenter;
import com.example.francis.examinationsystem.view.adapter.ExaminationAdapter;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.francis.examinationsystem.R.id.btn_course_tab_publish;

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
    @BindView(btn_course_tab_publish)
    FloatingActionButton btnCourseTabPublish;

    List<ExamPaper> lstExamPapers;
    ExaminationAdapter mExaminationAdapter;


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
        lstExamPapers = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        listExamination.setLayoutManager(manager);
        mExaminationAdapter = new ExaminationAdapter(lstExamPapers);
        listExamination.setAdapter(mExaminationAdapter);
        mExaminationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("examPaperId",lstExamPapers.get(position).getId());
                return false;
            }
        });
    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain, "考卷");
        if (App.mUser.getType() == 1) {
            btnCourseTabPublish.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadData() {
        mPresenter.queryExamList(getIntent().getLongExtra("courseId", -1));
    }

    @Override
    protected ExaminationPresenter createPresenter() {
        return new ExaminationPresenter();
    }

    @OnClick(btn_course_tab_publish)
    public void onClick() {
        toForResult(AddExaminatinoActivity.class, new Intent(), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {

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

    @Override
    public void loadExamListComplete(List<ExamPaper> lstExamPapers) {
        this.lstExamPapers.addAll(lstExamPapers);
        mExaminationAdapter.notifyDataSetChanged();
    }
}
