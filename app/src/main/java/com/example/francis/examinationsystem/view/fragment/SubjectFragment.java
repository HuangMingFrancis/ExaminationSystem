package com.example.francis.examinationsystem.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BaseFragment;
import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.base.MVPBaseFragment;
import com.example.francis.examinationsystem.contract.ISubjectFragmentView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.SubjectType;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.global.Constants;
import com.example.francis.examinationsystem.presenter.SubjectPresenter;
import com.example.francis.examinationsystem.view.activity.ExamActivity;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

import static android.R.attr.fragment;
import static android.R.attr.id;
import static android.R.attr.layout_width;
import static android.R.attr.switchMinWidth;
import static com.example.francis.examinationsystem.R.id.listSolution;
import static com.example.francis.examinationsystem.R.id.text;
import static com.example.francis.examinationsystem.R.id.tvFinishStatus;
import static com.example.francis.examinationsystem.R.id.tv_exam_value;

/**
 * Created by Francis on 2017/3/20.
 */

public class SubjectFragment extends MVPBaseFragment<ISubjectFragmentView, SubjectPresenter> implements ISubjectFragmentView {

    @BindView(R.id.tv_title_position)
    TextView tvTitlePosition;
    @BindView(R.id.fmSubjectContainer)
    FrameLayout fmSubjectContainer;
    private Subject mSubject;
    private Solution mSolution;
    private String mSubjectId;
    private ExamPaper mExamPaper;
    private Long mStudentId;
    private int mPosition;
    private SubjectType mSubjectType;
    private EditText etExamValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPosition == mExamPaper.getLstSubjectIds().size()) {
            loadView();
        }
    }

    public static SubjectFragment newInstance(String subjectId, ExamPaper examPaper, Long studentId, int position) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("subjectId", subjectId);
        bundle.putSerializable("examPaper", examPaper);
        bundle.putLong("studentId", studentId);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initVarious() {
        super.initVarious();
        mSubjectId = getArguments().getString("subjectId");
        mExamPaper = (ExamPaper) getArguments().getSerializable("examPaper");
        mStudentId = getArguments().getLong("studentId");
        mPosition = getArguments().getInt("position");
    }

    @Override
    protected SubjectPresenter createPresenter() {
        return new SubjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(mSubjectId)) {
            tvTitlePosition.setText((mPosition + 1) + "/" + mExamPaper.getLstSubjectIds().size());
        } else {
            tvTitlePosition.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        if (mSubject == null && !TextUtils.isEmpty(mSubjectId)) {
            mPresenter.querySubject(mSubjectId, mStudentId, mExamPaper.getId());
        } else {
            loadView();
        }
    }

    private void loadView() {
        View view = null;
        if (mSubjectType != null) {
            switch (mSubjectType.getId().intValue()) {
                case Constants.ExamType.EXAM_SINGLE:
                    view = getLayoutInflater(getArguments()).inflate(R.layout.item_exam_single, fmSubjectContainer, false);
                    RadioGroup rgExam = (RadioGroup) view.findViewById(R.id.rg_exam);
                    rgExam.removeAllViews();
                    int i = 0;
                    for (String text : mSubject.getOptions().split(";")) {
                        RadioButton radioButton = new RadioButton(mContext);
                        radioButton.setText(text);
                        radioButton.setId(i);
                        rgExam.addView(radioButton);
                        if (mSolution.getObjectId() != null) {
                            radioButton.setEnabled(false);
                        }
                        if (mSolution != null && mSolution.getResultValue() != null && mSolution.getResultValue().equals(String.valueOf(i))) {
                            radioButton.setChecked(true);
                        }
                        i++;
                    }
                    if (mSolution.getObjectId() == null) {
                        rgExam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                mSolution.setResultValue(String.valueOf(checkedId));
                            }
                        });
                    }
                    break;
                case Constants.ExamType.EXAM_JUDGE:
                    view = getLayoutInflater(getArguments()).inflate(R.layout.item_exam_single, fmSubjectContainer, false);
                    RadioGroup rgExam1 = (RadioGroup) view.findViewById(R.id.rg_exam);
                    if (mSolution.getObjectId() != null) {
                        for (int j = 0; j < rgExam1.getChildCount(); j++) {
                            rgExam1.getChildAt(j).setEnabled(false);
                        }

                    }
                    if (mSolution != null && mSolution.getResultValue() != null && mSolution.getResultValue().equals("true")) {
                        ((RadioButton) rgExam1.getChildAt(0)).setChecked(true);
                    } else if (mSolution != null && mSolution.getResultValue() != null && mSolution.getResultValue().equals("false")) {
                        ((RadioButton) rgExam1.getChildAt(1)).setChecked(true);
                    }
                    if (mSolution.getObjectId() == null) {
                        rgExam1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_exam_a) {
                                    mSolution.setResultValue("true");
                                } else {
                                    mSolution.setResultValue("false");
                                }

                            }
                        });
                    }
                    break;
                case Constants.ExamType.EXAM_MUTIPLE:
                    view = getLayoutInflater(getArguments()).inflate(R.layout.item_exam_mutiple, fmSubjectContainer, false);
                    LinearLayout llMutipleContainer = (LinearLayout) view.findViewById(R.id.llMutipleContainer);
                    int j = 0;
                    for (String text : mSubject.getOptions().split(";")) {
                        final CheckBox checkBox = new CheckBox(mContext);
                        checkBox.setText(text);
                        checkBox.setId(j);
                        if (mSolution.getObjectId() != null) {
                            checkBox.setEnabled(false);
                        } else {
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        if (TextUtils.isEmpty(mSolution.getResultValue())) {
                                            mSolution.setResultValue(String.valueOf(checkBox.getId()));
                                        } else if (!mSolution.getResultValue().contains(String.valueOf(checkBox.getId()))) {
                                            mSolution.setResultValue(mSolution.getResultValue() + ";" + checkBox.getId());
                                        }
                                    } else if (mSolution.getResultValue().contains(String.valueOf(checkBox.getId()))) {
                                        mSolution.getResultValue().replaceAll(";" + checkBox.getId(), "");
                                        mSolution.getResultValue().replaceAll(String.valueOf(checkBox.getId()), "");
                                    }

                                }
                            });
                        }
                        if (mSolution != null && mSolution.getResultValue() != null && mSolution.getResultValue().contains(String.valueOf(j))) {
                            checkBox.setChecked(true);
                        }
                        j++;
                        llMutipleContainer.addView(checkBox);
                    }

                    break;
                case Constants.ExamType.EXAM_SHORT:
                    view = getLayoutInflater(getArguments()).inflate(R.layout.item_exam_short, fmSubjectContainer, false);
                    final EditText etSolution = (EditText) view.findViewById(R.id.et_exam_content);
                    if (mSolution.getObjectId() != null) {
                        etSolution.setEnabled(false);
                        if (mSolution.getScore() < 0 && App.mUser.getType() == 0) {
                            etExamValue = (EditText) view.findViewById(R.id.et_exam_value);
                            etExamValue.setEnabled(true);
                        }
                    } else {
                        etSolution.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                mSolution.setResultValue(etSolution.getText().toString().trim());
                            }
                        });
                    }
                    if (!TextUtils.isEmpty(mSolution.getResultValue())) {
                        etSolution.setText(mSolution.getResultValue());
                    }
                    break;
            }
            TextView tvType = (TextView) view.findViewById(R.id.tv_exam_type);
            tvType.setText(mSubjectType.getName());
            TextView tvGrade = (TextView) view.findViewById(R.id.tv_exam_grade);
            tvGrade.setText(mSubject.getGrade().toString());
            TextView tvName = (TextView) view.findViewById(R.id.tv_exam_title);
            tvName.setText(mSubject.getName());
            TextView tvValue = (TextView) view.findViewById(R.id.tv_exam_value);
            tvValue.setText(mSolution == null ? "" : mSolution.getScore() == null ? "" : mSolution.getScore() >= 0 ? mSolution.getScore().toString() : "");
            if (mSolution != null && mSolution.getScore() != null && mSolution.getScore() >= 0) {
                TextView tvScore = (TextView) view.findViewById(tv_exam_value);
                tvScore.setText(mSolution.getScore().toString());
            }
        } else {
            view = getLayoutInflater(getArguments()).inflate(R.layout.item_exam_solution, fmSubjectContainer, false);

            final List<SubjectFragment> lstSubjectFragments = ((ExamActivity) getActivity()).getLstSubjectFragments();


            RecyclerView listSolution = (RecyclerView) view.findViewById(R.id.listSolution);
            listSolution.setLayoutManager(new GridLayoutManager(mContext, 5));
            List<Integer> lstNumber = new ArrayList<>();
            for (int i = 0; i < mExamPaper.getLstSubjectIds().size(); i++) {
                lstNumber.add(i);
            }
            BaseQuickAdapter adapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.item_solution_circle, lstNumber) {
                @Override
                protected void convert(BaseViewHolder helper, Integer item) {
                    if (lstSubjectFragments.get(item).getSolution() != null && !TextUtils.isEmpty(lstSubjectFragments.get(item).getSolution().getResultValue())) {
                        helper.setTextColor(R.id.tvSubjectNumber, R.color.colorAccent);
                    } else {
                        helper.setTextColor(R.id.tvSubjectNumber, R.color.actionsheet_red);
                    }
                    item++;
                    helper.setText(R.id.tvSubjectNumber, item.toString());
                }
            };
            listSolution.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ((ViewPager) getActivity().findViewById(R.id.vp_subject)).setCurrentItem(position);
                }
            });
            TextView tvFinishStatus = (TextView) view.findViewById(R.id.tvFinishStatus);
            int finishCount = 0;
            for (SubjectFragment fragment : lstSubjectFragments) {
                if (fragment.getSolution() != null && !TextUtils.isEmpty(fragment.getSolution().getResultValue())) {
                    finishCount++;
                }
            }
            tvFinishStatus.setText("已完成(" + finishCount + "/" + mExamPaper.getLstSubjectIds().size() + ")");

        }
        fmSubjectContainer.addView(view);
    }


    @Override
    public void loadSubjectComplete(Map<String, Object> dataMap) {
        mSubject = (Subject) dataMap.get("subject");
        mSubjectType = (SubjectType) dataMap.get("type");
        mSolution = (Solution) dataMap.get("solution");
        if (mSolution == null) {
            mSolution = new Solution();
            mSolution.setStudentId(App.mUser.getId());
            mSolution.setSubjectId(mSubject.getId());
            mSolution.setExamPaperId(mExamPaper.getId());
            mSolution.setScore(-1d);
        }
        loadView();
    }

    public Solution getSolution() {
        return mSolution;
    }

    /**
     * 系统自动得分
     */
    public boolean calculateGrade() {
        if (mSubjectType != null) {
            switch (mSubjectType.getId().intValue()) {
                case Constants.ExamType.EXAM_JUDGE:
                case Constants.ExamType.EXAM_SINGLE:
                    if (mSolution.getResultValue().equals(mSubject.getResultValue())) {
                        mSolution.setScore(mSubject.getGrade());
                    } else {
                        mSolution.setScore(0D);
                    }
                    break;
                case Constants.ExamType.EXAM_MUTIPLE:
                    List<String> solutionResult = Arrays.asList(mSolution.getResultValue().split(";"));
                    String[] subjectResult = mSolution.getResultValue().split(";");
                    if (solutionResult.size() != subjectResult.length) {
                        for (String result : subjectResult) {
                            if (!solutionResult.contains(result)) {
                                mSolution.setScore(0D);
                                return false;
                            }
                        }
                        mSolution.setScore(mSubject.getGrade());
                    } else {
                        mSolution.setScore(0D);
                    }
                    break;
                case Constants.ExamType.EXAM_SHORT:
                    if (etExamValue!=null&&!TextUtils.isEmpty(etExamValue.getText().toString())){

                    }else{
                        return false;
                    }
                    break;


            }
        }
        return true;
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
}
