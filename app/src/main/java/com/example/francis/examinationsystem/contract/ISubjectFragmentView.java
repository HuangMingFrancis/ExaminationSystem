package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;

import java.util.Map;

/**
 * Created by wzn on 2017/3/25.
 */

public interface ISubjectFragmentView extends IBaseView {
    void loadSubjectComplete(Map<String,Object> dataMap);
}
