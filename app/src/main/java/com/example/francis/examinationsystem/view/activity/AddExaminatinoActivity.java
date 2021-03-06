package com.example.francis.examinationsystem.view.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.MVPBaseActivity;
import com.example.francis.examinationsystem.contract.IAddExaminationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.presenter.AddExaminationPresenter;
import com.example.francis.examinationsystem.util.TimeUtils;
import com.example.francis.examinationsystem.util.Toaster;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Francis on 2017/3/21.
 */

public class AddExaminatinoActivity extends MVPBaseActivity<IAddExaminationView, AddExaminationPresenter> implements IAddExaminationView {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.et_exam_title)
    EditText etExamTitle;
    @BindView(R.id.et_exam_content)
    EditText etExamContent;
    @BindView(R.id.tv_add_exam_startDate)
    TextView tvAddExamStartDate;
    @BindView(R.id.tv_add_exam_startTime)
    TextView tvAddExamStartTime;
    @BindView(R.id.tv_add_exam_endDate)
    TextView tvAddExamEndDate;
    @BindView(R.id.tv_add_exam_endTime)
    TextView tvAddExamEndTime;
    @BindView(R.id.btn_exam_submit)
    Button btnExamSubmit;


    private int mYearStart, mYearEnd;
    private int mMonthStart, mMonthEnd;
    private int mDayStart, mDayEnd;
    private int mHourStart, mHourEnd;
    private int mMinuteStart, mMinuteEnd;

    //时间类型 0：开始时间 1：结束时间
    private int timeType = 0;

    private Calendar mCalendar;

    private Long courseId;

    //编辑跳转过来会转一个examPaper
    private ExamPaper examPaperEdit;

    //examPaper状态 0: 新增  1:编辑
    private int examPaperState = 0;

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
        return R.layout.activity_addexam_title;
    }

    @Override
    protected void initData() {

        mCalendar = Calendar.getInstance();
        mYearStart = mCalendar.get(Calendar.YEAR);
        mMonthStart = mCalendar.get(Calendar.MONTH) + 1;
        mDayStart = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHourStart = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinuteStart = mCalendar.get(Calendar.MINUTE);
        mYearEnd = mCalendar.get(Calendar.YEAR);
        mMonthEnd = mCalendar.get(Calendar.MONTH) + 1;
        mDayEnd = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHourEnd = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinuteEnd = mCalendar.get(Calendar.MINUTE);
        tvAddExamStartDate.setText(TimeUtils.getCurrentDateFormat(mCalendar));
        tvAddExamStartTime.setText(TimeUtils.getCurrentTimeFormat(mCalendar));
        tvAddExamEndDate.setText(TimeUtils.getCurrentDateFormat(mCalendar));
        tvAddExamEndTime.setText(TimeUtils.getCurrentTimeFormat(mCalendar));

        if (getIntent() != null) {
            courseId = getIntent().getLongExtra("courseId", -1);
            examPaperEdit = (ExamPaper) getIntent().getSerializableExtra("examPaper");
            if (examPaperEdit != null) {
                examPaperState = 1;
                etExamTitle.setText(examPaperEdit.getName());
                etExamContent.setText(examPaperEdit.getDes());
            } else {
                examPaperState = 0;
            }
        }
    }

    @Override
    protected void initView() {
        setToolBar(toolbarMain, "新测试");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected AddExaminationPresenter createPresenter() {
        return new AddExaminationPresenter();
    }


    @OnClick({R.id.tv_add_exam_startDate, R.id.tv_add_exam_startTime, R.id.tv_add_exam_endDate, R.id.tv_add_exam_endTime, R.id.btn_exam_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_exam_startDate:
                timeType = 0;
                showDateDialog();
                break;
            case R.id.tv_add_exam_startTime:
                timeType = 0;
                showTimeDialog();
                break;
            case R.id.tv_add_exam_endDate:
                timeType = 1;
                showDateDialog();
                break;
            case R.id.tv_add_exam_endTime:
                timeType = 1;
                showTimeDialog();
                break;
            case R.id.btn_exam_submit:
                if (TextUtils.isEmpty(etExamTitle.getText())) {
                    showToast("测试题目不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etExamContent.getText())) {
                    showToast("测试简介不能为空");
                    return;
                }
                if (examPaperState == 0) {
                    Intent intent = new Intent();
                    ExamPaper examPaper = new ExamPaper();
                    examPaper.setName(etExamTitle.getText().toString());
                    examPaper.setDes(etExamContent.getText().toString());
                    examPaper.setCourseId(courseId);
                    try {
                        examPaper.setPlanStartDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").
                                parse(tvAddExamStartDate.getText().toString() + " " + tvAddExamStartTime.getText().toString()).getTime());
                        examPaper.setPlanEndDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").
                                parse(tvAddExamEndDate.getText().toString() + " " + tvAddExamEndTime.getText().toString()).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("examPaper", examPaper);
                    to(AddExamActivity.class, intent);
                    finish();
                } else if (examPaperState == 1) {
                    examPaperEdit.setName(etExamTitle.getText().toString());
                    examPaperEdit.setDes(etExamContent.getText().toString());
                    try {
                        examPaperEdit.setPlanStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").
                                parse(tvAddExamStartDate.getText().toString() + tvAddExamStartTime.getText().toString()).getTime());
                        examPaperEdit.setPlanEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").
                                parse(tvAddExamEndDate.getText().toString() + tvAddExamEndTime.getText().toString()).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mPresenter.editExamPaper(examPaperEdit);
                }

                break;
        }
    }

    /**
     * 时间选择器
     */
    private void showTimeDialog() {
        new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (timeType == 0) {
                    mHourStart = hourOfDay;
                    mMinuteStart = minute;
                    mCalendar.set(Calendar.HOUR_OF_DAY, mHourStart);
                    mCalendar.set(Calendar.MINUTE, mMinuteStart);
                    tvAddExamStartTime.setText(hourOfDay + ":" + minute);
                } else {
                    mHourEnd = hourOfDay;
                    mMinuteEnd = minute;
                    mCalendar.set(Calendar.HOUR_OF_DAY, mHourEnd);
                    mCalendar.set(Calendar.MINUTE, mMinuteEnd);
                    tvAddExamEndTime.setText(hourOfDay + ":" + minute);
                }
            }
        }, timeType == 0 ? mHourStart : mHourEnd, timeType == 0 ? mMinuteStart : mMinuteEnd, true).show();
    }

    /**
     * 日期选择器
     */
    private void showDateDialog() {
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (timeType == 0) {
                    mYearStart = year;
                    mMonthStart = monthOfYear + 1;
                    mDayStart = dayOfMonth;
                    mCalendar.set(mYearStart, mMonthStart - 1, mDayStart);
                    tvAddExamStartDate.setText(mYearStart + "/" + mMonthStart + "/" + mDayStart);
                } else {
                    mYearEnd = year;
                    mMonthEnd = monthOfYear + 1;
                    mDayEnd = dayOfMonth;
                    mCalendar.set(mYearEnd, mMonthEnd - 1, mDayEnd);
                    tvAddExamEndDate.setText(mYearEnd + "/" + mMonthEnd + "/" + mDayEnd);
                }
            }
        }, timeType == 0 ? mYearStart : mYearEnd, timeType == 0 ? mMonthStart - 1 : mMonthEnd - 1, timeType == 0 ? mDayStart : mDayEnd).show();
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
    public void editExamPaperSuccess() {
        finish();
    }
}
