package com.example.francis.examinationsystem.presenter;

import android.util.ArrayMap;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IClassRoomView;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.BmobErrorData;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.model.course.CourseModel;
import com.example.francis.examinationsystem.util.net.BmobErrorAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Francis on 2017/3/18.
 */

public class ClassRoomPresenter extends BasePresenter<IClassRoomView> {
    CourseModel courseModel;

    public ClassRoomPresenter() {
        courseModel = new CourseModel();
    }

    public void addCourse(Course course) {
        getView().showLoading();
        final String courseName=course.getName();
        courseModel.addCourse(course)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        getView().hideLoading();
                        course.setName(courseName);
                        getView().addCourseSuccess(course);
                    }
                }, new BmobErrorAction() {
                    @Override
                    public void call(BmobErrorData errorData) {
                        getView().hideLoading();
                        getView().showToast(errorData.getError());
                    }
                });
    }

    public void queryCourseList() {
        getView().showLoading();
        Map<String, Object> conditions = new HashMap<>();
        if (App.mUser.getType()==0){
            conditions.put("teacherId", App.mUser.getId());
        }else{
//            conditions.put("");
        }
        courseModel.queryCourseList(conditions)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        getView().hideLoading();
                        getView().loadCourseListComplete(courses);
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
