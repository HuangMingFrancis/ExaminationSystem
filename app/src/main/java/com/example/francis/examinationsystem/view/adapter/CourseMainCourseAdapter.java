package com.example.francis.examinationsystem.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.Course;

import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public class CourseMainCourseAdapter extends BaseQuickAdapter<Course, BaseViewHolder>{
    public CourseMainCourseAdapter(int layoutResId, List<Course> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Course course) {
        helper.setText(R.id.tv_item_courseName,course.getName());
        if (course.getLstStudents()!=null){
            helper.setText(R.id.tv_item_StudentCount,"参与学生: "+course.getLstStudents().size()+"人");
        }

        helper.addOnClickListener(R.id.img_item_courseMore);
    }
}
