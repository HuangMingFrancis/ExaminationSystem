package com.example.francis.examinationsystem.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Francis on 2017/3/20.
 */

public class ExamFragment extends BaseFragment {

    @BindView(R.id.tv_paper_titie)
    TextView tvPaperTitie;
    private int position;

    public static ExamFragment newInstance(int position) {
        ExamFragment fragment = new ExamFragment();
        fragment.setPosition(position);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void initView() {
        tvPaperTitie.setText("title: "+position);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    public void setPosition(int position) {
        this.position = position;
    }

}
