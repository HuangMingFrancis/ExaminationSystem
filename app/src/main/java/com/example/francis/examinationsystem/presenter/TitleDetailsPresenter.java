package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ITitleDetailsView;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.global.Constants;

/**
 * Created by Francis on 2017/3/21.
 */

public class TitleDetailsPresenter extends BasePresenter<ITitleDetailsView>{


    private Subject subject;

    public void getExamTypeId(){

    }


    public void addExam(String grade,String title,int examType,String resultValue,String options){
        if (TextUtils.isEmpty(grade)){
            getView().showToast("分数不能为空！");
            return;
        }
        if (TextUtils.isEmpty(title)){
            getView().showToast("题目不能为空！");
            return;
        }
        if (TextUtils.isEmpty(resultValue) && examType!=Constants.ExamType.EXAM_SHORT){
            getView().showToast("答案不能为空！");
            return;
        }

        subject=new Subject();
        subject.setGrade(Double.valueOf(grade));
        subject.setResultValue(resultValue);
        subject.setSubjectTypeId((long) examType);
        subject.setOptions(options);
        subject.setName(title);

        getView().getSubject(subject);

    }


}
