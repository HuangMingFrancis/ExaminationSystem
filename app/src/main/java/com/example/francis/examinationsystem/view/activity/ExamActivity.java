package com.example.francis.examinationsystem.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.TimeUtils;
import com.example.francis.examinationsystem.util.Toaster;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;
import com.example.francis.examinationsystem.view.fragment.SubjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.example.francis.examinationsystem.R.id.btnSubmitSolution;

/**
 * Created by Francis on 2017/3/20.
 */

public class ExamActivity extends AppCompatActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_exam_name)
    TextView tvExamName;
    @BindView(R.id.tv_bottom_left)
    TextView tvBottomLeft;
    @BindView(R.id.tv_bottom_right)
    TextView tvBottomRight;
    @BindView(R.id.vp_subject)
    ViewPager vpSubject;
    @BindView(R.id.btnSubmitSolution)
    Button btnSubmitSolution;

    private List<SubjectFragment> lstSubjectFragments = new ArrayList<>();
    private SubjectPageAdapter examPageAdapter;
    private ExamPaper mExamPaper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mExamPaper = (ExamPaper) getIntent().getSerializableExtra("examPaper");
        Long studentId = null;
        if (App.mUser.getType() == 0) {

        } else {
            studentId = App.mUser.getId();
        }
        for (int i = 0; i < mExamPaper.getLstSubjectIds().size(); i++) {
            lstSubjectFragments.add(SubjectFragment.newInstance(mExamPaper.getLstSubjectIds().get(i), mExamPaper, studentId, i));
        }
        examPageAdapter = new SubjectPageAdapter(getSupportFragmentManager());
        vpSubject.setAdapter(examPageAdapter);
    }

    private void initView() {
        tvExamName.setText(mExamPaper.getName());
        btnSubmitSolution = (Button) findViewById(R.id.btnSubmitSolution);
        vpSubject.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == lstSubjectFragments.size()) {
                    btnSubmitSolution.setVisibility(View.VISIBLE);
                } else {
                    btnSubmitSolution.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnSubmitSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Solution> solutions = new ArrayList<>();
                for (SubjectFragment fragment : lstSubjectFragments) {
                    if (fragment.getSolution() == null || TextUtils.isEmpty(fragment.getSolution().getResultValue())) {
                        Toaster.showLong("请做完题目，再提交。");
                        return;
                    } else {
                        fragment.calculateGrade();
                        solutions.add(fragment.getSolution());
                    }
                }
                ExamModel examModel = new ExamModel();
                examModel.addSolutionList(solutions)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean success) {
                                if (success){
                                    Toaster.showLong("提交成功");
                                    finish();
                                }else{
                                    Toaster.showLong("提交失败");
                                }
                            }
                        }, new BmobErrorAction() {
                            @Override
                            public void call(BmobErrorData errorData) {
                                Toaster.showLong(errorData.getError());
                            }
                        });

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_bottom_left, R.id.tv_bottom_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_bottom_left:
                if (vpSubject.getCurrentItem() != 0) {
                    vpSubject.setCurrentItem(vpSubject.getCurrentItem() - 1);
                }
                break;
            case R.id.tv_bottom_right:
                if (vpSubject.getCurrentItem() != examPageAdapter.getCount() - 1) {
                    vpSubject.setCurrentItem(vpSubject.getCurrentItem() + 1);
                }
                break;
        }
    }

    class SubjectPageAdapter extends FragmentPagerAdapter {

        public SubjectPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == lstSubjectFragments.size()) {
                return SubjectFragment.newInstance(null, mExamPaper, -1L, position);
            }

            return lstSubjectFragments.get(position);
        }

        @Override
        public int getCount() {
            return lstSubjectFragments.size() + 1;
        }
    }

    public List<SubjectFragment> getLstSubjectFragments() {
        return lstSubjectFragments;
    }
}
