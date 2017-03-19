package com.example.francis.examinationsystem.entity;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 课程
 * Created by wzn on 2017/3/19.
 */

public class Course extends BmobObject {
    private Long id;
    private String name;
    /**
     * 课程类型
     */
    private CourseType courseType;
    /**
     * 老师
     */
    private User teacher;
    /**
     * 学生
     */
    private List<User> lstStudents;
    private String studentIds;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<User> getLstStudents() {
        return lstStudents;
    }

    public void setLstStudents(List<User> lstStudents) {
        this.lstStudents = lstStudents;
    }
}
