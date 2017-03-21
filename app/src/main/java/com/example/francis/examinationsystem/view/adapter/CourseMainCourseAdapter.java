package com.example.francis.examinationsystem.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;

import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public class CourseMainCourseAdapter extends BaseQuickAdapter<String, BaseViewHolder>{
    public CourseMainCourseAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_courseName,item);
    }
}
