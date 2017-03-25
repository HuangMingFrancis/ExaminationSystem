package com.example.francis.examinationsystem.view.adapter;

import android.telecom.Call;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.entity.Subject;
import com.example.francis.examinationsystem.view.activity.AccountActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public class ChooseSubjectAdapte extends BaseQuickAdapter<Subject,BaseViewHolder> {
    private List<Subject> data=new ArrayList<>();
    private CallBack callBack;
    private int type;

    public ChooseSubjectAdapte(int layoutResId, List<Subject> data,CallBack callBack,int type) {
        super(layoutResId, data);
        this.data=data;
        this.callBack=callBack;
        this.type=type;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Subject item) {

        switch (type){
            case 1:
                helper.setVisible(R.id.cb_choose,false);
                break;

            case 2:
                helper.setVisible(R.id.cb_choose,true);
                break;
        }
        helper.setChecked(R.id.cb_choose,data.get(helper.getPosition()).isChecked());
        helper.setText(R.id.tv_exam_title,item.getName());
        helper.setText(R.id.tv_exam_grade,item.getGrade()+"");

        helper.setOnCheckedChangeListener(R.id.cb_choose, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(helper.getPosition()).setChecked(isChecked);
                List<Subject> checks=new ArrayList<Subject>();
                for (Subject s:
                     data) {
                    if (s.isChecked()){
                        checks.add(s);
                    }
                }
                callBack.checkSubjectList(checks);

            }
        });

    }

    public interface CallBack{
        void checkSubjectList(List<Subject> checkSujects);
    }
}
