package com.example.francis.examinationsystem.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BasePresenterFragment;
import com.example.francis.examinationsystem.contract.ISearchTabView;
import com.example.francis.examinationsystem.presenter.SearchTabPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Francis on 2017/3/18.
 */

public class SearchTabFragment extends BasePresenterFragment<ISearchTabView, SearchTabPresenter> implements ISearchTabView {

    @BindView(R.id.list_search_tab)
    RecyclerView listSearchTab;
    @BindView(R.id.fresh_search_tab)
    SwipeRefreshLayout freshSearchTab;
    private int position;

    @Override
    protected SearchTabPresenter createPresenter() {
        return new SearchTabPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_tab;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static SearchTabFragment newInstance(int position) {
        SearchTabFragment fragment = new SearchTabFragment();
        fragment.setPosition(position);
        return fragment;
    }

}
