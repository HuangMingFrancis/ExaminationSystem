package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.SolutionsAll;

import java.util.List;

/**
 * Created by Francis on 2017/3/26.
 */

public interface IExamSituationView extends IBaseView {
    void getSolutionList(List<SolutionsAll> solutions);
}
