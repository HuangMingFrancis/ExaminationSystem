package com.example.francis.examinationsystem.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.ExamPaper;

import java.util.List;

/**
 * Created by wzn on 2017/3/22.
 */

public class ExaminationAdapter extends BaseQuickAdapter<ExamPaper, BaseViewHolder> {
    public ExaminationAdapter(int layoutResId, List<ExamPaper> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamPaper item) {
        helper.setText(android.R.id.text1,item.getName());
    }
}
