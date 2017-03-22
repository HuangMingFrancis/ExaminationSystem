package com.example.francis.examinationsystem.view.adapter;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.Course;
import com.example.francis.examinationsystem.entity.ExamPaper;
import com.example.francis.examinationsystem.global.App;
import com.example.francis.examinationsystem.util.TimeUtils;

import java.util.List;

/**
 * Created by wzn on 2017/3/22.
 */

public class ExaminationAdapter extends BaseQuickAdapter<ExamPaper, BaseViewHolder> {


    public ExaminationAdapter(List<ExamPaper> data) {
        super(data);
    }

    @Override
    public int getItemViewType(int position) {
        return App.mUser.getType();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_student_exam_paper, parent, false);
        }
        return new BaseViewHolder(view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamPaper item) {
        if (App.mUser.getType()==0){
            helper.setText(android.R.id.text1, item.getName());
        }else{
           helper.setText(R.id.tv_exam_title,item.getName());
//            helper.setText(R.id.tv_exam_content,item.getde)
            helper.setText(R.id.tv_exam_endTime,"截至"+ TimeUtils.getNotificationTime(item.getPlanEndDate().getTime()));
            helper.setText(R.id.tv_exam_publishTime,TimeUtils.getNotificationTime(item.getPlanEndDate().getTime()));
        }
    }
}
