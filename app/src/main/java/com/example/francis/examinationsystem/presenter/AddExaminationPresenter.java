package com.example.francis.examinationsystem.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IAddExaminationView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/21.
 */

public class AddExaminationPresenter extends BasePresenter<IAddExaminationView> {

    private ExamModel examModel;
    public AddExaminationPresenter() {
        examModel=new ExamModel();
    }

    //编辑状态点击确认修改考卷
    public void editExamPaper(ExamPaper examPaper){
        getView().showLoading();
        examModel.updateExamPaper(examPaper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JSONObject>() {
                    @Override
                    public void call(JSONObject jsonObject) {
                        getView().hideLoading();
                        getView().editExamPaperSuccess();
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
