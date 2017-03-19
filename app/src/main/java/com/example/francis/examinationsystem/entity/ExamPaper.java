package com.example.francis.examinationsystem.entity;

import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 试卷
 * Created by wzn on 2017/3/19.
 */

public class ExamPaper extends BmobObject {
    private Long id;
    private String name;
    /**
     * 课程类型
     */
    private CourseType courseType;
    /**
     * 课程
     */
    private Course course;
    /**
     * 题目
     */
    private List<Subject> lstSubjects;
    /**
     * 开始时间
     */
    private Date planStartDate;
    /**
     * 结束时间
     */
    private Date planEndDate;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Subject> getLstSubjects() {
        return lstSubjects;
    }

    public void setLstSubjects(List<Subject> lstSubjects) {
        this.lstSubjects = lstSubjects;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }
}
