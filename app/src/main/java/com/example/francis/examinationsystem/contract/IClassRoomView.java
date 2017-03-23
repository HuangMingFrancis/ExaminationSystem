package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.Course;

import java.util.List;

/**
 * Created by Francis on 2017/3/18.
 */

public interface IClassRoomView extends IBaseView {
    void addCourseSuccess(Course course);
    void loadCourseListComplete(List<Course> lstCourse);
    void deleteCourseSuccess(Course course);
}
