package com.example.francis.examinationsystem.presenter;

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

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

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
        final String courseName = course.getName();
        if (App.mUser.getType() == 0) {
            courseModel.addCourse(course)
                    .flatMap(new Func1<Course, Observable<Course>>() {
                        @Override
                        public Observable<Course> call(Course course) {
                            course.setCreatedAt(null);
                            course.setUpdatedAt(null);
                            return courseModel.queryCourse(course);
                        }
                    })
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
        } else {
            courseModel.joinCourse(course.getName(),App.mUser.getId())
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
    }

    public void queryCourseList() {
        getView().showLoading();
        Map<String, Object> conditions = new HashMap<>();
        if (App.mUser.getType() == 0) {
            conditions.put("teacherId", App.mUser.getId());
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("$in", new Long[]{App.mUser.getId()});
            conditions.put("lstStudents", map);
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

    public void deleteCourse(final Course course){
        getView().showLoading();
        courseModel.deleteCourse(course.getObjectId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        getView().hideLoading();
                        if (aBoolean){
                            getView().deleteCourseSuccess(course);
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

}
