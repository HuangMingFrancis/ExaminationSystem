package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ITitleDetailsView;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.TitleDetailsPresenter;
import com.example.francis.examinationsystem.util.Toaster;

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

    //正确答案
    private String resultValue="";
    //选项
    private String options="";

    private Subject subject;

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
            subject= (Subject) getIntent().getSerializableExtra("subject");
            switch (examType){
                case Constants.ExamType.EXAM_JUDGE:
                    view= LayoutInflater.from(mContext).inflate(R.layout.item_add_exam_judge,null);
                    rg_add_exam= (RadioGroup) view.findViewById(R.id.rg_add_exam);
                    rg_add_exam.setOnCheckedChangeListener(this);
                    RadioButton rb_add_exam_true= (RadioButton) view.findViewById(R.id.rb_add_exam_true);
                    RadioButton rb_add_exam_false= (RadioButton) view.findViewById(R.id.rb_add_exam_false);
                    if (subject!=null){
                        if (subject.getResultValue().equals("true")){
                            rb_add_exam_false.setChecked(false);
                            rb_add_exam_true.setChecked(true);
                        }else{
                            rb_add_exam_false.setChecked(false);
                            rb_add_exam_true.setChecked(true);
                        }
                    }
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
                    if (subject!=null){
                        String options=subject.getOptions();
                        et_add_exam_a.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_a.getText().toString()+";","");

                        et_add_exam_b.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_b.getText().toString()+";","");

                        et_add_exam_c.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_c.getText().toString()+";","");

                        et_add_exam_d.setText(options.substring(0,options.indexOf(";")));


                        if (subject.getResultValue().contains("1")){
                            cb_add_exam_a.setChecked(true);
                        }
                        if (subject.getResultValue().contains("2")){
                            cb_add_exam_b.setChecked(true);
                        }
                        if (subject.getResultValue().contains("3")){
                            cb_add_exam_c.setChecked(true);
                        }
                        if (subject.getResultValue().contains("4")){
                            cb_add_exam_d.setChecked(true);
                        }
                    }
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

                    RadioButton rb_add_exam_a= (RadioButton) view.findViewById(R.id.rb_add_exam_a);
                    RadioButton rb_add_exam_b= (RadioButton) view.findViewById(R.id.rb_add_exam_b);
                    RadioButton rb_add_exam_c= (RadioButton) view.findViewById(R.id.rb_add_exam_c);
                    RadioButton rb_add_exam_d= (RadioButton) view.findViewById(R.id.rb_add_exam_d);

                    if (subject!=null){
                        String options=subject.getOptions();
                        et_add_exam_a.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_a.getText().toString()+";","");

                        et_add_exam_b.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_b.getText().toString()+";","");

                        et_add_exam_c.setText(options.substring(0,options.indexOf(";")));
                        options.replace(et_add_exam_c.getText().toString()+";","");

                        et_add_exam_d.setText(options.substring(0,options.indexOf(";")));


                        if (subject.getResultValue().contains("1")){
                            rb_add_exam_a.setChecked(true);
                        }
                        if (subject.getResultValue().contains("2")){
                            rb_add_exam_b.setChecked(true);
                        }
                        if (subject.getResultValue().contains("3")){
                            rb_add_exam_c.setChecked(true);
                        }
                        if (subject.getResultValue().contains("4")){
                            rb_add_exam_d.setChecked(true);
                        }
                    }

                    break;
            }
            flytExamContent.addView(view);

            et_add_exam_grade= (EditText) view.findViewById(R.id.et_add_exam_grade);
            et_add_exam_title= (EditText) view.findViewById(R.id.et_add_exam_title);
            btn_add_exam_submit= (Button) view.findViewById(R.id.btn_add_exam_submit);

            if (subject!=null){
                et_add_exam_title.setText(subject.getName());
                et_add_exam_grade.setText(subject.getGrade()+"");
            }

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
        switch (examType){
            case Constants.ExamType.EXAM_JUDGE:
                mPresenter.addExam(et_add_exam_grade.getText().toString(),et_add_exam_title.getText().toString(),examType,resultValue,options);
                break;

            case Constants.ExamType.EXAM_SINGLE:
                if (judgeOption())
                    mPresenter.addExam(et_add_exam_grade.getText().toString(),et_add_exam_title.getText().toString(),examType,resultValue,options);
                break;

            case Constants.ExamType.EXAM_MUTIPLE:
                if (judgeOption()){
                    if (cb_add_exam_a.isChecked())
                        resultValue=resultValue+"1;";

                    if (cb_add_exam_b.isChecked())
                        resultValue=resultValue+"2;";

                    if (cb_add_exam_c.isChecked())
                        resultValue=resultValue+"3;";

                    if (cb_add_exam_d.isChecked())
                        resultValue=resultValue+"4;";

                    mPresenter.addExam(et_add_exam_grade.getText().toString(),et_add_exam_title.getText().toString(),examType,resultValue,options);
                }
                break;

            case Constants.ExamType.EXAM_SHORT:
                mPresenter.addExam(et_add_exam_grade.getText().toString(),et_add_exam_title.getText().toString(),examType,resultValue,options);
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_add_exam_true:
                resultValue="true";
                break;

            case R.id.rb_add_exam_false:
                resultValue="false";
                break;
            case R.id.rb_add_exam_a:
                resultValue="1";
                break;
            case R.id.rb_add_exam_b:
                resultValue="2";
                break;
            case R.id.rb_add_exam_c:
                resultValue="2";
                break;
            case R.id.rb_add_exam_d:
                resultValue="3";
                break;

        }
    }


    private boolean judgeOption(){
        if (TextUtils.isEmpty(et_add_exam_a.getText().toString())){
            showToast("选项不完整，请检查！");
            return false;
        }
        options=options+et_add_exam_a.getText().toString()+";";
        if (TextUtils.isEmpty(et_add_exam_b.getText().toString())){
            showToast("选项不完整，请检查！");
            return false;
        }
        options=options+et_add_exam_b.getText().toString()+";";
        if (TextUtils.isEmpty(et_add_exam_c.getText().toString())){
            showToast("选项不完整，请检查！");
            return false;
        }
        options=options+et_add_exam_c.getText().toString()+";";
        if (TextUtils.isEmpty(et_add_exam_d.getText().toString())){
            showToast("选项不完整，请检查！");
            return false;
        }
        options=options+et_add_exam_d.getText().toString()+";";
        return true;
    }


    @Override
    public void getSubject(Subject subject) {
        Intent intent=new Intent();
        intent.putExtra("subject",subject);
        setResult(1,intent);
        finish();
    }
}
