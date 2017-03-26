package com.example.francis.examinationsystem.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 2017/3/26.
 */

public class SolutionsAll {
    private String studentName;
    private String date;
    private Double gradle;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getGradle() {
        return gradle;
    }

    public void setGradle(Double gradle) {
        this.gradle = gradle;
    }

    public String getStudentName() {

        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
