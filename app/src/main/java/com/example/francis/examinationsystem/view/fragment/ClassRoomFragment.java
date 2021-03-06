package com.example.francis.examinationsystem.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BasePresenterFragment;
import com.example.francis.examinationsystem.contract.IClassRoomView;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.presenter.ClassRoomPresenter;
import com.example.francis.examinationsystem.util.NetUtils;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.activity.ExaminationActivity;
import com.example.francis.examinationsystem.view.adapter.CourseMainCourseAdapter;
import com.example.francis.examinationsystem.view.thirty.MyPopupMenu;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/18.
 */

public class ClassRoomFragment extends BasePresenterFragment<IClassRoomView, ClassRoomPresenter> implements IClassRoomView, DialogInterface.OnDismissListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_main_add)
    FloatingActionButton btnMainAdd;
    @BindView(R.id.list_main_course)
    RecyclerView listMainCourse;
    @BindView(R.id.fresh_main_course)
    SwipeRefreshLayout freshMainCourse;

    //判断addBtn是否open
    private boolean isBtnOpen = true;

    //addBtn的动画
    private Animation mAddOpenAnim;
    private Animation mAddCloseAnim;

    private AlertDialog mAddDialog;

    private BaseQuickAdapter mMainCourseAdapter;

    private List<Course> mCourses;

    private MyPopupMenu mCourseEditPopupMenu;

    @Override
    protected ClassRoomPresenter createPresenter() {
        return new ClassRoomPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class_room;
    }

    @Override
    public void showToast(String message) {
        Toaster.showShort(message);
//        Snackbar.make(btnMainAdd, message, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        super.initView();
        listMainCourse.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void initData() {
        super.initData();
        initAddBtnAnim();

        initAdapter();

    }

    @Override
    protected void loadData() {
        mPresenter.queryCourseList();
    }

    @Override
    protected void initListener() {
        super.initListener();

        mMainCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ExaminationActivity.class);
                intent.putExtra("courseId", mCourses.get(position).getId());
                startActivity(intent);
            }
        });

        mMainCourseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.img_item_courseMore:
                        initEditPopMenu(view,position);
                        break;
                }
                return false;
            }
        });

        freshMainCourse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.queryCourseList();
            }
        });
    }


    private void initEditPopMenu(View view, final int position){
        mCourseEditPopupMenu = new MyPopupMenu(mContext, view, R.menu.teacher_courese_edit_menu);
        mCourseEditPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_menu_delete:
                        mPresenter.deleteCourse(mCourses.get(position));
                        break;
                }
                return true;
            }
        });
        mCourseEditPopupMenu.show();
    }


    private void initAdapter() {
        mCourses = new ArrayList<>();
        mMainCourseAdapter = new CourseMainCourseAdapter(R.layout.item_main_course, mCourses);
        listMainCourse.setAdapter(mMainCourseAdapter);

    }

    //addAnim
    private void initAddBtnAnim() {
        mAddCloseAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_close);
        mAddOpenAnim = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_open);
    }

    @OnClick(R.id.btn_main_add)
    public void onClick() {
        changeAddBtnAnim();
        showAddDialog();
    }

    //AddBtn open or close时设置动画
    private void changeAddBtnAnim() {

        if (isBtnOpen) {
            btnMainAdd.startAnimation(mAddOpenAnim);
            isBtnOpen = false;
        } else {
            btnMainAdd.startAnimation(mAddCloseAnim);
            isBtnOpen = true;
        }
    }

    private void showAddDialog() {
        mAddDialog = new AlertDialog.Builder(mContext).create();
        mAddDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_add, null);
        TextView dialogTitle = (TextView) view.findViewById(R.id.tv_addDialog_title);
        final EditText dialogCourse = (EditText) view.findViewById(R.id.et_addDialog_courseName);
        Button btnCancel = (Button) view.findViewById(R.id.btn_addDialog_cancel);
        Button btnCreate = (Button) view.findViewById(R.id.btn_addDialog_create);

        //设置事件监听
        mAddDialog.setOnDismissListener(this);

        if (App.mUser.getType() == 0) {
            dialogTitle.setText("新建班级");
            dialogCourse.setHint("请输入新建班级名称");
            btnCreate.setText("创建");
        } else {
            dialogTitle.setText("加入班级");
            dialogCourse.setHint("请输入班级名称");
            btnCreate.setText("加入");
        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddDialog.dismiss();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetUtils.isConnected()) {
                    Course course = new Course();
                    if (App.mUser.getType() == 0) {
                        course.setName(dialogCourse.getText().toString());
                        course.setTeacherId(App.mUser.getId());
                    }else{
                        course.setName(dialogCourse.getText().toString());
                    }
                    mPresenter.addCourse(course);
                } else {
                    showToast("没有网络连接");
                }
            }
        });


        mAddDialog.setView(view);
        mAddDialog.show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        changeAddBtnAnim();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void addCourseSuccess(Course course) {
        mCourses.add(course);
        mMainCourseAdapter.notifyDataSetChanged();
        mAddDialog.dismiss();
    }

    @Override
    public void loadCourseListComplete(List<Course> lstCourse) {
        if (freshMainCourse.isRefreshing()){
            freshMainCourse.setRefreshing(false);
        }
        mCourses.clear();
        mCourses.addAll(lstCourse);
        mMainCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCourseSuccess(Course course) {
        mCourses.remove(course);
        mMainCourseAdapter.notifyDataSetChanged();
    }
}
