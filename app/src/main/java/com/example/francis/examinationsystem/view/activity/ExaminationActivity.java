package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IExaminationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.presenter.ExaminationPresenter;
import com.example.francis.examinationsystem.view.adapter.ExaminationAdapter;
import com.example.francis.examinationsystem.view.thirty.MyPopupMenu;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.francis.examinationsystem.R.id.btn_course_tab_publish;

/**
 * Created by Francis on 2017/3/21.
 */

public class ExaminationActivity extends MVPBaseActivity<IExaminationView, ExaminationPresenter> implements IExaminationView
    ,BaseQuickAdapter.OnItemChildClickListener,SwipeRefreshLayout.OnRefreshListener{
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

    private Long courseId;

    private MyPopupMenu editExamPopup;

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

        if (getIntent()!=null){
            courseId=getIntent().getLongExtra("courseId",-1);
        }

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

        mExaminationAdapter.setOnItemChildClickListener(this);

        freshExamination.setOnRefreshListener(this);
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
//        mPresenter.queryExamList(courseId);
    }

    @Override
    protected ExaminationPresenter createPresenter() {
        return new ExaminationPresenter();
    }

    @OnClick(btn_course_tab_publish)
    public void onClick() {
        Intent intent =new Intent();
        intent.putExtra("courseId",courseId);
        toForResult(AddExaminatinoActivity.class, intent, 0);
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
        if (freshExamination.isRefreshing()){
            freshExamination.setRefreshing(false);
        }
        this.lstExamPapers.clear();
        this.lstExamPapers.addAll(lstExamPapers);
        mExaminationAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteExamPaperSuccess(ExamPaper examPaper) {
        lstExamPapers.remove(examPaper);
        mExaminationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryExamList(courseId);
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.img_t_exam_edit:
                initPopupMenu(view,position);
                break;
        }
        return false;
    }

    private void initPopupMenu(View view, final int position) {
        editExamPopup=new MyPopupMenu(mContext,view,R.menu.exampaper_edit_menu);
        editExamPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_edit:
                        Intent intent=new Intent();
                        intent.putExtra("examPaper",lstExamPapers.get(position));
                        to(AddExaminatinoActivity.class,intent);
                        break;
                    case R.id.item_menu_delete:
                        mPresenter.deleteExamPaper(lstExamPapers.get(position));
                        break;
                }
                return false;
            }
        });
        editExamPopup.show();
    }

    @Override
    public void onRefresh() {
        mPresenter.queryExamList(courseId);
    }
}
