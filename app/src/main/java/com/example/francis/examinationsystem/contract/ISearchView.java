package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.Course;

import java.util.List;

/**
 * Created by Francis on 2017/3/18.
 */

public interface ISearchView extends IBaseView {

    void getSearchCourseList(List<Course> searchCourseList);
    void deleteCourseSuccess(Course course);
}
