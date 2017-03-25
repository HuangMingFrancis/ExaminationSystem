package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IAddExamView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public void addExamPager(ExamPaper examPaper,final List<Subject> subjects){

        examModel.addExamPaper(examPaper,subjects)
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

    public void searchSubject(final String name, int subjectTypeId){
        getView().showLoading();
        Map<String,Object> searchMaps=new HashMap<>();
        searchMaps.put("subjectTypeId",subjectTypeId);
        examModel.querySubjectList(JSONObject.toJSONString(searchMaps))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Subject>>() {
                    @Override
                    public void call(List<Subject> subjects) {
                        getView().hideLoading();
                        List<Subject> subjects1=new ArrayList<Subject>();
                        for (Subject subject:
                             subjects) {
                            if (subject.getName().contains(name)){
                                subjects1.add(subject);
                            }
                        }
                        getView().searchSubjectSuccess(subjects1);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().showToast(errorData.getError());
                    }
                });

    }


}
