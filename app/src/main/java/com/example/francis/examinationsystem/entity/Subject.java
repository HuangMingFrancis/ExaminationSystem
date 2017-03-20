package com.example.francis.examinationsystem.entity;



/**
 * 题目
 * Created by wzn on 2017/3/19.
 */

public class Subject {
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


}
