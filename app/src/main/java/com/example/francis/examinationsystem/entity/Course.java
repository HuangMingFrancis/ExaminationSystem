package com.example.francis.examinationsystem.entity;

import java.util.List;


/**
 * 课程
 * Created by wzn on 2017/3/19.
 */

public class Course extends BaseObject{
    private Long id;
    private String name;
    /**
     * 课程类型
     */
    private Long courseTypeId;
    /**
     * 老师
     */
    private Long teacherId;
    /**
     * 学生
     */
    private List<Long> lstStudents;

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

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Long> getLstStudents() {
        return lstStudents;
    }

    public void setLstStudents(List<Long> lstStudents) {
        this.lstStudents = lstStudents;
    }
}
