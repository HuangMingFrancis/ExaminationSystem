package com.example.francis.examinationsystem.entity;


/**
 * 课程类型
 * Created by wzn on 2017/3/19.
 */

public class CourseType extends BaseObject {
    private Long id;
    private String name;

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
}
