package com.example.francis.examinationsystem.entity;

import java.util.Date;
import java.util.List;



/**
 * 试卷
 * Created by wzn on 2017/3/19.
 */

public class ExamPaper  {
    private Long id;
    private String name;
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
    private List<Long> lstSubjectIds;
    /**
     * 开始时间
     */
    private Date planStartDate;
    /**
     * 结束时间
     */
    private Date planEndDate;

}
