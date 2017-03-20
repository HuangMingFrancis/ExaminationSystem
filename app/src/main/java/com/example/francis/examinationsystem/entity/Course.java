package com.example.francis.examinationsystem.entity;

import java.util.List;


/**
 * 课程
 * Created by wzn on 2017/3/19.
 */

public class Course {
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


}
