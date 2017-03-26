package com.example.francis.examinationsystem.presenter;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ISubjectFragmentView;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.Map;
import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by wzn on 2017/3/25.
 */

public class SubjectPresenter extends BasePresenter<ISubjectFragmentView> {
    ExamModel examModel;

    public SubjectPresenter() {
        examModel = new ExamModel();
    }

    public void querySubject(String subjectId, Long studentId, Long examPaperId) {
        getView().showLoading();
        examModel.querySubject(subjectId, studentId, examPaperId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map<String, Object>>() {
                    @Override
                    public void call(Map<String, Object> map) {
                        getView().hideLoading();
                        getView().loadSubjectComplete(map);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }

    public void querySolution(Long subjectId, Long userId, Long examPaperId) {
        examModel.querySolution(subjectId, userId, examPaperId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Solution>() {
                    @Override
                    public void call(Solution solution) {

                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {

                    }
                });
    }


}
