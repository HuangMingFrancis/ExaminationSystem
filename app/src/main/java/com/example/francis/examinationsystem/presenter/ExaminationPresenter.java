package com.example.francis.examinationsystem.presenter;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IExaminationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/21.
 */

public class ExaminationPresenter extends BasePresenter<IExaminationView> {
    ExamModel examModel;

    public ExaminationPresenter() {
        examModel = new ExamModel();
    }

    public void queryExamList(Long courseId) {
        getView().showLoading();
        examModel.queryExamPaperList(courseId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ExamPaper>>() {
                    @Override
                    public void call(List<ExamPaper> examPapers) {
                        getView().hideLoading();
                        getView().loadExamListComplete(examPapers);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }
}
