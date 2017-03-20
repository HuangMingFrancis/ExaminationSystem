package com.example.francis.examinationsystem.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.view.fragment.ExamFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/20.
 */

public class ExamActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_answer_sheet)
    ImageView ivAnswerSheet;
    @BindView(R.id.tv_bottom_left)
    TextView tvBottomLeft;
    @BindView(R.id.tv_bottom_right)
    TextView tvBottomRight;
    @BindView(R.id.vp_subject)
    ViewPager vpSubject;

    private List<ExamFragment> examFragments=new ArrayList<>();
    private ExamPageAdapter examPageAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        for (int i=0;i<10;i++){
            examFragments.add(ExamFragment.newInstance(i));
        }
        examPageAdapter=new ExamPageAdapter(getSupportFragmentManager());
        vpSubject.setAdapter(examPageAdapter);
    }

    @OnClick({R.id.iv_back, R.id.iv_answer_sheet, R.id.tv_bottom_left, R.id.tv_bottom_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_answer_sheet:
                break;
            case R.id.tv_bottom_left:
                break;
            case R.id.tv_bottom_right:
                break;
        }
    }

    class ExamPageAdapter extends FragmentPagerAdapter {

        public ExamPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return examFragments.get(position);
        }

        @Override
        public int getCount() {
            return examFragments.size();
        }
    }


}
