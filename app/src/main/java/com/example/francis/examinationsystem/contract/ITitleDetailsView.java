package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.Subject;

/**
 * Created by Francis on 2017/3/21.
 */

public interface ITitleDetailsView extends IBaseView {

    void getSubject(Subject subject);
}
