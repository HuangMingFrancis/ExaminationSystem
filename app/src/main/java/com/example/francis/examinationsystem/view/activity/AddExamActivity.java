package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IAddExamView;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.AddExamPresenter;
import com.example.francis.examinationsystem.view.adapter.ChooseSubjectAdapte;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/21.
 */

public class AddExamActivity extends MVPBaseActivity<IAddExamView, AddExamPresenter> implements IAddExamView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.btn_exam_judge)
    FloatingActionButton btnExamJudge;
    @BindView(R.id.btn_exam_single_selection)
    FloatingActionButton btnExamSingleSelection;
    @BindView(R.id.btn_exam_multiple_selection)
    FloatingActionButton btnExamMultipleSelection;
    @BindView(R.id.btn_exam_short_answer)
    FloatingActionButton btnExamShortAnswer;
    @BindView(R.id.btn_exam_add)
    FloatingActionsMenu btnExamAdd;

    private BaseQuickAdapter mChooseSubjectAdapte;
    private List<String> titles;

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
        return R.layout.activity_addexam;
    }

    @Override
    protected void initData() {
        titles=new ArrayList<>();
        mChooseSubjectAdapte=new ChooseSubjectAdapte(R.layout.item_choose_exam_subject,titles);
    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain,"试卷标题");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected AddExamPresenter createPresenter() {
        return new AddExamPresenter();
    }


    @OnClick({R.id.btn_exam_judge, R.id.btn_exam_single_selection, R.id.btn_exam_multiple_selection, R.id.btn_exam_short_answer, R.id.btn_exam_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exam_judge:
                showSubjectChooseDialog(1);
                break;
            case R.id.btn_exam_single_selection:
                showSubjectChooseDialog(2);
                break;
            case R.id.btn_exam_multiple_selection:
                showSubjectChooseDialog(3);
                break;
            case R.id.btn_exam_short_answer:
                showSubjectChooseDialog(4);
                break;
            case R.id.btn_exam_add:
                break;
        }
    }

    private void showSubjectChooseDialog(final int type) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose_exam_subject, null);
        RecyclerView listChooseExamSubjects = (RecyclerView) view.findViewById(R.id.list_choose_exam_subject);
        CheckBox cbChooseExamAll = (CheckBox) view.findViewById(R.id.cb_choose_exam_allCheck);
        Button btnChooseExamCreate = (Button) view.findViewById(R.id.btn_choose_exam_create);
        Button btnChooseExamAdd = (Button) view.findViewById(R.id.btn_choose_exam_add);
        final EditText etChooseExamSearch = (EditText) view.findViewById(R.id.et_choose_exam_search);

        etChooseExamSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listChooseExamSubjects.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        listChooseExamSubjects.setAdapter(mChooseSubjectAdapte);

        cbChooseExamAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChooseSubjectAdapte.notifyDataSetChanged();
            }
        });


        btnChooseExamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnChooseExamCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                switch (type) {
                    case 1:
                        intent.putExtra("examType", Constants.ExamType.EXAM_JUDGE);
                        to(TitleDetailsActivity.class,intent);
                        break;
                    case 2:
                        intent.putExtra("examType", Constants.ExamType.EXAM_SINGLE);
                        to(TitleDetailsActivity.class,intent);
                        break;
                    case 3:
                        intent.putExtra("examType", Constants.ExamType.EXAM_MUTIPLE);
                        to(TitleDetailsActivity.class,intent);
                        break;
                    case 4:
                        intent.putExtra("examType", Constants.ExamType.EXAM_SHORT);
                        to(TitleDetailsActivity.class,intent);
                        break;
                }
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_exam_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
