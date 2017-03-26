package com.example.francis.examinationsystem.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IExamSituationView;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.SolutionsAll;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.model.exam.ExamModel;
import com.example.francis.examinationsystem.model.user.UserModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/26.
 */

public class ExamSituationPresenter extends BasePresenter<IExamSituationView> {
    private ExamModel examModel;
    private UserModel userModel;
    public ExamSituationPresenter() {
        examModel=new ExamModel();
        userModel=new UserModel();
    }

    public void querySolutionList(Long examPaperId){
        getView().showLoading();
        final Map<String,Object> solutionMap=new HashMap<>();
//        solutionMap.put("examPaperId",examPaperId);
        solutionMap.put("examPaperId",12l);
        examModel.querySolutionList(JSONObject.toJSONString(solutionMap))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Solution>>() {
                    @Override
                    public void call(List<Solution> solutions) {
                        getView().hideLoading();

                        if (solutions.size()<=0){
                            List<SolutionsAll> users=new ArrayList<SolutionsAll>();
                            getView().getSolutionList( users);
                        }else{
                            queryUserByIds(solutions);
                        }
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }

    public void queryUserByIds(final List<Solution> solutions){
        Map<String , Object> map=new HashMap<>();
        Map<String,Object> map1=new HashMap<>();
        List<Long> nameIds=new ArrayList<>();
        for (Solution s:
             solutions) {
            nameIds.add(s.getStudentId());
        }
        map1.put("$in",nameIds);
        map.put("id",map1);
        userModel.queryUserByIds(JSONObject.toJSONString(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        List<SolutionsAll> solutionsAndUsers=new ArrayList<SolutionsAll>();
                        for (int i=0;i<users.size();i++) {
                            SolutionsAll solutionsAll=new SolutionsAll();
                            double gradle=0;
                            String date="";
                            for (int j=0;j<solutions.size();j++) {
                                if (solutions.get(j).getStudentId() == users.get(i).getId()) {
                                    gradle += solutions.get(j).getScore();
                                    date = solutions.get(j).getCreatedAt();
                                }
                            }
                            solutionsAll.setDate(date);
                            solutionsAll.setStudentName(users.get(i).getUserName());
                            solutionsAll.setGradle(gradle);
                            solutionsAndUsers.add(solutionsAll);

                        }
                        getView().getSolutionList(solutionsAndUsers);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().showToast(errorData.getError());
                    }
                });
    }
}
