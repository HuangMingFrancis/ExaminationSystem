package com.example.francis.examinationsystem.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.Solution;
import com.example.francis.examinationsystem.entity.SolutionsAll;
import com.example.francis.examinationsystem.entity.User;
import com.example.francis.examinationsystem.model.user.UserModel;

import java.util.List;

/**
 * Created by Francis on 2017/3/26.
 */

public class SolutionsAdapter extends BaseQuickAdapter<SolutionsAll,BaseViewHolder> {

    private UserModel userModel;

    public SolutionsAdapter(int layoutResId, List<SolutionsAll> data) {
        super(layoutResId, data);
        userModel=new UserModel();
    }

    @Override
    protected void convert(final BaseViewHolder helper, SolutionsAll item) {
        helper.setText(R.id.tv_t_exam_commit_time,item.getDate());
        helper.setText(R.id.tv_t_exam_student_grade,item.getGradle()+"");
        helper.setText(R.id.tv_t_exam_student_name,item.getStudentName());

    }
}
