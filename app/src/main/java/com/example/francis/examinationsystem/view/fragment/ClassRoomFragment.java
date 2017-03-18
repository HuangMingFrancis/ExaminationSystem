package com.example.francis.examinationsystem.view.fragment;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.base.BasePresenterFragment;
import com.example.francis.examinationsystem.contract.IClassRoomView;
import com.example.francis.examinationsystem.presenter.ClassRoomPresenter;
import com.example.francis.examinationsystem.util.Toaster;

/**
 * Created by Francis on 2017/3/18.
 */

public class ClassRoomFragment extends BasePresenterFragment <IClassRoomView,ClassRoomPresenter> implements IClassRoomView{
    @Override
    protected ClassRoomPresenter createPresenter() {
        return new ClassRoomPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class_room;
    }

    @Override
    public void showToast(String message) {
        Toaster.showShort(message);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
