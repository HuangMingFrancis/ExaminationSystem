package com.example.francis.examinationsystem.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Francis on 2017/3/21.
 */

public class ChooseSubjectAdapte extends BaseQuickAdapter<String,BaseViewHolder> {
    public ChooseSubjectAdapte(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
