package com.example.francis.examinationsystem.entity;



/**
 * 题目
 * Created by wzn on 2017/3/19.
 */

public class Subject extends BaseObject{
    private Long id;
    private String name;
    /**
     * 题目类型
     */
    private Long subjectTypeId;
    /**
     * 课程类型
     */
    private Long courseTypeId;
    /**
     * 题目正确答案
     */
    private String resultValue;
    /**
     * 得分
     */
    private Double grade;
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

    public Long getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(Long subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
