package com.example.francis.examinationsystem.entity;

import cn.bmob.v3.BmobObject;

/**
 * 题目
 * Created by wzn on 2017/3/19.
 */

public class Subject extends BmobObject {
    private Long id;
    private String name;
    /**
     * 题目类型
     */
    private SubjectType subjectType;
    /**
     * 课程类型
     */
    private CourseType courseType;
    /**
     * 题目正确答案
     */
    private String resultValue;
    /**
     * 得分
     */
    private Long grade;



    /**
     * 选项
     */
    private String options;


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

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
