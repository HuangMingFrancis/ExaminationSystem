package com.example.francis.examinationsystem.presenter;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.ISearchView;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.course.CourseModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/18.
 */

public class SearchPresenter extends BasePresenter<ISearchView> {

    private CourseModel courseModel;
    public SearchPresenter() {
        courseModel=new CourseModel();
    }

    /**
     * 搜索课程
     * @param courseName
     */
    public void searchExam(final String courseName){
        getView().showLoading();
//        String bql="select * from Course where teacherId="+App.mUser.getId();
//        List<Object> values=new ArrayList<>();
//        values.add(App.mUser.getId());
//        Map<String, Object> conditions = new HashMap<>();
//        conditions.put("teacherId", App.mUser.getId());
//        courseModel.queryCourseListByCourseName(bql,values)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<Course>>() {
//                    @Override
//                    public void call(List<Course> courses) {
//                        getView().hideLoading();
//                        getView().getSearchCourseList(courses);
//                    }
//                }, new BmobErrorAction() {
//                    @Override
//                    public void call(BmobErrorData errorData) {
//                        getView().hideLoading();
//                        getView().showToast(errorData.getError());
//                    }
//                });

//        Map<String, Object> conditions = new HashMap<>();
//        if (App.mUser.getType()==0){
//            conditions.put("teacherId", App.mUser.getId());
//        }else{
////            conditions.put("");
//        }
        String hql="select * from Course where teacherId=? and name like ?";
        List<Object> conditions=new ArrayList<>();
        conditions.add(7);
        conditions.add("‘%"+courseName+"%’");
        courseModel.queryCourseListByCourseName(hql,conditions)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        getView().hideLoading();
//                        List<Course> courses1=new ArrayList<Course>();
//                        for (Course course:courses){
//                            if (course.getName().contains(courseName))
//                                courses1.add(course);
//                        }
//                        getView().getSearchCourseList(courses1);
                        getView().getSearchCourseList(courses);
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
