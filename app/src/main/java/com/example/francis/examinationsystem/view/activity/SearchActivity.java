package com.example.francis.examinationsystem.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ISearchView;
import com.example.francis.examinationsystem.presenter.SearchPresenter;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.view.adapter.SearchTabPagerAdapter;
import com.example.francis.examinationsystem.view.thirty.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private SearchTabPagerAdapter mSearchTabsPagerAdapter;

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

    }

    @Override
    protected void initView() {

        initTabsLayout();

    }

    @Override
    protected void loadData() {

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
}
