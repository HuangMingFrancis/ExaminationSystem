package com.example.francis.examinationsystem.presenter;

import android.text.TextUtils;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ITitleDetailsView;

/**
 * Created by Francis on 2017/3/21.
 */

public class TitleDetailsPresenter extends BasePresenter<ITitleDetailsView>{



    public void addExamJudge(String grade,String title){
        if (judgeGradeAndTitle(grade,title)){

        }
    }

    public void addExamSingle(String grade,String title){
        if (judgeGradeAndTitle(grade,title)){

        }
    }

    public void addExamMutiple(String grade,String title){
        if (judgeGradeAndTitle(grade,title)){

        }
    }

    public void addExamShort(String grade,String title){
        if (judgeGradeAndTitle(grade,title)){

        }
    }



    private boolean judgeGradeAndTitle(String grade,String title){
        if (TextUtils.isEmpty(grade)){
            getView().showToast("分数不能为空！");
            return false;
        }
        if (TextUtils.isEmpty(title)){
            getView().showToast("题目不能为空！");
            return false;
        }
        return true;
    }

}
