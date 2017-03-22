package com.example.francis.examinationsystem.presenter;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IAddExamView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/21.
 */

public class AddExamPresenter extends BasePresenter<IAddExamView> {

    private ExamModel examModel;
    private List<Long> subjectIds;


    public AddExamPresenter() {
        examModel=new ExamModel();
        subjectIds=new ArrayList<>();
    }

    public void addSubjects(final List<Subject> subjects){
        subjectIds.clear();
        examModel.addSubjectList(subjects)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        if (longs!=null){
                            getView().addSubjectsSuccess(longs);
                        }
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().showToast(errorData.getError());
                    }
                });
    }
    public void addExamPager(final List<Long> subjectIds,ExamPaper examPaper){
        examPaper.setLstSubjectIds(subjectIds);

        examModel.addExamPaper(examPaper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ExamPaper>() {
                    @Override
                    public void call(ExamPaper examPaper) {
                        if (examPaper!=null)
                            getView().addExamPaperSuccess(examPaper);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().showToast(errorData.getError());
                    }
                });
    }


}
