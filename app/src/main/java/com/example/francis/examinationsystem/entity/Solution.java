package com.example.francis.examinationsystem.entity;



/**
 * 答题值表
 * Created by wzn on 2017/3/19.
 */

public class Solution extends BaseObject {
    private Long id;
    /**
     * 答案
     */
    private String resultValue;
    /**
     * 题目
     */
    private Long subjectId;
    /**
     * 答题学生
     */
    private Long studentId;
    /**
     * 试卷
     */
    private Long examPaperId;
    /**
     * 得分
     */
    private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Long examPaperId) {
        this.examPaperId = examPaperId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
