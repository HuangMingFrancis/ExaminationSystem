package com.example.francis.examinationsystem.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.ITitleDetailsView;
import com.example.francis.examinationsystem.presenter.TitleDetailsPresenter;

import butterknife.BindView;

/**
 * Created by Francis on 2017/3/21.
 */

public class TitleDetailsActivity extends MVPBaseActivity<ITitleDetailsView, TitleDetailsPresenter> implements ITitleDetailsView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;



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
        setToolBar(toolbarMain,"题目类型");
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

}
