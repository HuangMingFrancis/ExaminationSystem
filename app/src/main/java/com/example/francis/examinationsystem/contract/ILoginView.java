package com.example.francis.examinationsystem.contract;

import com.example.francis.examinationsystem.base.IBaseView;
import com.example.francis.examinationsystem.entity.User;

/**
 * Created by Francis on 2017-3-10.
 */

public interface ILoginView extends IBaseView{
    void loginSuccess();
    void registerSuccess(String userName);
}
