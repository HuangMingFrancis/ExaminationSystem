package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ISearchView;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.presenter.SearchPresenter;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.adapter.CourseMainCourseAdapter;
import com.example.francis.examinationsystem.view.adapter.SearchTabPagerAdapter;
import com.example.francis.examinationsystem.view.thirty.MyPopupMenu;
import com.example.francis.examinationsystem.view.thirty.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/18.
 */

public class SearchActivity extends MVPBaseActivity<ISearchView, SearchPresenter> implements ISearchView {
    @BindView(R.id.img_search_back)
    ImageView imgSearchBack;
    @BindView(R.id.et_search_text)
    EditText etSearchText;
    @BindView(R.id.stl_search_tabs)
    SlidingTabLayout stlSearchTabs;
    @BindView(R.id.vp_search_content)
    ViewPager vpSearchContent;
    @BindView(R.id.list_search_course)
    RecyclerView listSearchCourse;
    @BindView(R.id.fresh_search_course)
    SwipeRefreshLayout freshSearchCourse;
    private SearchTabPagerAdapter mSearchTabsPagerAdapter;

    private List<Course> searchCourse;
    private BaseQuickAdapter searchAdapter;

    private MyPopupMenu mCourseEditPopupMenu;

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
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        searchCourse=new ArrayList<>();
        searchAdapter= new CourseMainCourseAdapter(R.layout.item_main_course, searchCourse);
        listSearchCourse.setAdapter(searchAdapter);

    }

    @Override
    protected void initView() {

//    initTabsLayout();

        listSearchCourse.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("http", "beforeTextChanged: "+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("http", "onTextChanged: "+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("http", "afterTextChanged: "+s.toString());
                mPresenter.searchExam(s.toString());
            }
        });



        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchActivity.this, ExaminationActivity.class);
                intent.putExtra("courseId", searchCourse.get(position).getId());
                startActivity(intent);
            }
        });

        searchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

        freshSearchCourse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.searchExam(etSearchText.getText().toString());
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
                        mPresenter.deleteCourse(searchCourse.get(position));
                        break;
                }
                return true;
            }
        });
        mCourseEditPopupMenu.show();
    }

    @Override
    protected void loadData() {
        mPresenter.searchExam(etSearchText.getText().toString());
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }


    @OnClick(R.id.img_search_back)
    public void onClick() {
        finish();
    }

    private void initTabsLayout() {

        mSearchTabsPagerAdapter = new SearchTabPagerAdapter(((AppCompatActivity) mContext).getSupportFragmentManager(), mContext);
        vpSearchContent.setAdapter(mSearchTabsPagerAdapter);
        stlSearchTabs.setViewPager(vpSearchContent);
        stlSearchTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
    }

    @Override
    public void getSearchCourseList(List<Course> searchCourseList) {
        if (freshSearchCourse.isRefreshing()){
            freshSearchCourse.setRefreshing(false);
        }
        if (searchCourseList!=null){
            searchCourse.clear();
            searchCourse.addAll(searchCourseList);
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteCourseSuccess(Course course) {
        searchCourse.remove(course);
        searchAdapter.notifyDataSetChanged();
    }
}
