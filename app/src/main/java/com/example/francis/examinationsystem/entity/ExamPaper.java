package com.example.francis.examinationsystem.entity;

import java.util.Date;
import java.util.List;



/**
 * 试卷
 * Created by wzn on 2017/3/19.
 */

public class ExamPaper extends BaseObject {
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

    public ExamPaper(Long id, String name, Long courseTypeId, Long courseId, List<Long> lstSubjectIds, Date planStartDate, Date planEndDate) {
        this.id = id;
        this.name = name;
        this.courseTypeId = courseTypeId;
        this.courseId = courseId;
        this.lstSubjectIds = lstSubjectIds;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
    }
}
