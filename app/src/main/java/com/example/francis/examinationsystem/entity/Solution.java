package com.example.francis.examinationsystem.entity;

import cn.bmob.v3.BmobObject;

/**
 * 答题值表
 * Created by wzn on 2017/3/19.
 */

public class Solution extends BmobObject {
    private Long id;
    /**
     * 答案
     */
    private String resultValue;
    /**
     * 题目
     */
    private Subject subject;
    /**
     * 答题学生
     */
    private User student;
    /**
     * 试卷
     */
    private ExamPaper examPaper;
    /**
     * 得分
     */
    private Long score;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
