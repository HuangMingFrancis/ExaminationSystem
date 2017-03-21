package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.ExamPaper;

import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public interface IExaminationView extends IBaseView{
    void loadExamListComplete(List<ExamPaper> lstExamPapers);
}
