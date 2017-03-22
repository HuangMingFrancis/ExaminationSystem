package com.example.francis.examinationsystem.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ITitleDetailsView;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.TitleDetailsPresenter;

import butterknife.BindView;

/**
 * Created by Francis on 2017/3/21.
 */

public class TitleDetailsActivity extends MVPBaseActivity<ITitleDetailsView, TitleDetailsPresenter> implements ITitleDetailsView
    ,View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.flyt_exam_content)
    FrameLayout flytExamContent;

    //共有 分数,题目,确认按钮
    private EditText et_add_exam_grade;
    private EditText et_add_exam_title;
    private Button btn_add_exam_submit;

    //判断题 和 单选题
    private RadioGroup rg_add_exam;
    //多选题和单选题的各个选项
    private EditText et_add_exam_a,et_add_exam_b,et_add_exam_c,et_add_exam_d;
    //多选题的按钮
    private CheckBox cb_add_exam_a,cb_add_exam_b,cb_add_exam_c,cb_add_exam_d;


    //题目类型
    private int examType=0;

    //具体题型界面
    private View view;

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
        return R.layout.activity_title_details_judge;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain, "题目类型");

        if (getIntent()!=null){
            examType=getIntent().getIntExtra("examType",0);
            switch (examType){
                case Constants.ExamType.EXAM_JUDGE:
                    view= LayoutInflater.from(mContext).inflate(R.layout.item_add_exam_judge,null);
                    rg_add_exam= (RadioGroup) view.findViewById(R.id.rg_add_exam);
                    rg_add_exam.setOnCheckedChangeListener(this);
                    break;
                case Constants.ExamType.EXAM_MUTIPLE:
                    view= LayoutInflater.from(mContext).inflate(R.layout.item_add_exam_multiple_selection,null);
                    et_add_exam_a= (EditText) view.findViewById(R.id.et_add_exam_a);
                    et_add_exam_b= (EditText) view.findViewById(R.id.et_add_exam_b);
                    et_add_exam_c= (EditText) view.findViewById(R.id.et_add_exam_c);
                    et_add_exam_d= (EditText) view.findViewById(R.id.et_add_exam_d);
                    cb_add_exam_a= (CheckBox) view.findViewById(R.id.cb_add_exam_a);
                    cb_add_exam_b= (CheckBox) view.findViewById(R.id.cb_add_exam_b);
                    cb_add_exam_c= (CheckBox) view.findViewById(R.id.cb_add_exam_c);
                    cb_add_exam_d= (CheckBox) view.findViewById(R.id.cb_add_exam_d);
                    break;
                case Constants.ExamType.EXAM_SHORT:
                    view= LayoutInflater.from(mContext).inflate(R.layout.item_add_exam_short_answer,null);
                    break;
                case Constants.ExamType.EXAM_SINGLE:
                    view= LayoutInflater.from(mContext).inflate(R.layout.item_add_exam_single_selection,null);
                    rg_add_exam= (RadioGroup) view.findViewById(R.id.rg_add_exam);
                    et_add_exam_a= (EditText) view.findViewById(R.id.et_add_exam_a);
                    et_add_exam_b= (EditText) view.findViewById(R.id.et_add_exam_b);
                    et_add_exam_c= (EditText) view.findViewById(R.id.et_add_exam_c);
                    et_add_exam_d= (EditText) view.findViewById(R.id.et_add_exam_d);
                    rg_add_exam.setOnCheckedChangeListener(this);
                    break;
            }
            flytExamContent.addView(view);

            et_add_exam_grade= (EditText) view.findViewById(R.id.et_add_exam_grade);
            et_add_exam_title= (EditText) view.findViewById(R.id.et_add_exam_title);
            btn_add_exam_submit= (Button) view.findViewById(R.id.btn_add_exam_submit);

            btn_add_exam_submit.setOnClickListener(this);
        }

    }


    @Override
    protected void loadData() {

    }

    @Override
    protected TitleDetailsPresenter createPresenter() {
        return new TitleDetailsPresenter();
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
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
