package com.example.francis.examinationsystem.model.course;

import com.alibaba.fastjson.JSONObject;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.bmob.DataResult;
import com.example.francis.examinationsystem.util.net.RetrofitHelper;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wzn on 2017/3/21.
 */

public class CourseModel {
    CourseService courseService;

    public CourseModel() {
        courseService = RetrofitHelper.getRetrofit().create(CourseService.class);
    }


    public Observable<Course> addCourse(Course course) {
        return courseService.addCourse(course).subscribeOn(Schedulers.io());
    }

    public Observable<Course> queryCourse(Course course) {
        return courseService.queryCourseList(JSONObject.toJSONString(course))
                .flatMap(new Func1<DataResult<Course>, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(DataResult<Course> dataResult) {
                        if (dataResult.results.size() > 0) {
                            return Observable.just(dataResult.results.get(0));
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Course>> queryCourseList(Map<String, Object> conditons) {
        return courseService.queryCourseList(JSONObject.toJSONString(conditons))
                .flatMap(new Func1<DataResult<Course>, Observable<List<Course>>>() {
                    @Override
                    public Observable<List<Course>> call(final DataResult<Course> dataResult) {
                        return Observable.just(dataResult.results);
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<Course> joinCourse(final String objectId, final Long studentId) {
        Course course = new Course();
        course.setObjectId(objectId);
        return queryCourse(course)
                .flatMap(new Func1<Course, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Course course) {
                        Course newCourse = new Course();
                        newCourse.setLstStudents(course.getLstStudents());
                        course.getLstStudents().add(studentId);
                        return courseService.updateCourse(objectId, course);
                    }
                })
                .subscribeOn(Schedulers.io());

    }

    public Observable<Course> updateCourse(Course course) {
        return courseService.updateCourse(course.getObjectId(), course)
                .subscribeOn(Schedulers.io());
    }

    public Observable<Boolean> deleteCourse(String objectId) {
        return courseService.deleteCourse(objectId)
                .flatMap(new Func1<JSONObject, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(final JSONObject jsonObject) {
                        if (jsonObject.get("msg").equals("ok")) {
                            return Observable.just(true);
                        } else {
                            return Observable.just(false);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Course>> queryCourseListByCourseName(String bql,Map<String, Object> conditons) {
        return courseService.queryCourseListByCourseName(bql,JSONObject.toJSONString(conditons))
                .flatMap(new Func1<DataResult<Course>, Observable<List<Course>>>() {
                    @Override
                    public Observable<List<Course>> call(final DataResult<Course> dataResult) {
                        return Observable.just(dataResult.results);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
