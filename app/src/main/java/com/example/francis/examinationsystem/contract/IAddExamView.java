package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.entity.Subject;

import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public interface IAddExamView extends IBaseView {

    void addSubjectsSuccess(List<Long> subjectIds);

    void addExamPaperSuccess(ExamPaper examPaper);

    void searchSubjectSuccess(List<Subject> searchSubjects);
}
