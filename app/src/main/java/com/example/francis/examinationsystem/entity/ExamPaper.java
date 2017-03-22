package com.example.francis.examinationsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 试卷
 * Created by wzn on 2017/3/19.
 */

public class ExamPaper extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    /**
     * 课程类型
     */
    private Long courseTypeId;
    /**
     * 课程
     */
    private Long courseId;
    /**
     * 题目
     */
    private List<String> lstSubjectIds;
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

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<String> getLstSubjectIds() {
        return lstSubjectIds;
    }

    public void setLstSubjectIds(List<String> lstSubjectIds) {
        this.lstSubjectIds = lstSubjectIds;
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
