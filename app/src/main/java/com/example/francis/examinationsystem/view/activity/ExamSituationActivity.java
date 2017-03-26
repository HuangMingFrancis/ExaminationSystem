package com.example.francis.examinationsystem.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IExamSituationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.SolutionsAll;
import com.example.francis.examinationsystem.presenter.ExamSituationPresenter;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.adapter.SolutionsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 学生考试情况
 * Created by Francis on 2017/3/26.
 */

public class ExamSituationActivity extends MVPBaseActivity<IExamSituationView, ExamSituationPresenter> implements IExamSituationView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.list_exam_situation)
    RecyclerView listExamSituation;
    @BindView(R.id.fresh_exam_situation)
    SwipeRefreshLayout freshExamSituation;


    private List<Solution> solutions;
    private BaseQuickAdapter solutionAdapter;
    private ExamPaper examPaper;
    private List<SolutionsAll> solutionsAndUserses;


    @Override
    protected int getLayout() {
        return R.layout.activity_exam_situation;
    }

    @Override
    protected void initData() {

        listExamSituation.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        solutions=new ArrayList<>();
        solutionsAndUserses=new ArrayList<>();
        solutionAdapter=new SolutionsAdapter(R.layout.item_exam,solutionsAndUserses);
        listExamSituation.setAdapter(solutionAdapter);
        if (getIntent()!=null){
            examPaper= (ExamPaper) getIntent().getSerializableExtra("examPaper");

        }

    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain,"答题情况");

    }

    @Override
    protected void loadData() {
        if (examPaper!=null)
        mPresenter.querySolutionList(examPaper.getId());
    }

    @Override
    protected ExamSituationPresenter createPresenter() {
        return new ExamSituationPresenter();
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
    public void getSolutionList(List<SolutionsAll> solutions) {
        this.solutionsAndUserses.clear();
        this.solutionsAndUserses.addAll(solutions);
        solutionAdapter.notifyDataSetChanged();

    }
}
