package com.example.francis.examinationsystem.presenter;

import com.example.francis.examinationsystem.base.BasePresenter;
import com.example.francis.examinationsystem.contract.IFlashView;
import com.example.francis.examinationsystem.util.sp.ProjectSPUtils;

/**
 * Created by Francis on 2017-3-15.
 */

public class FlashPresenter extends BasePresenter<IFlashView> {
    private boolean isFirstIn = false; // 是否为第一次启动应用
    private boolean isLogin = false; // 是否已登录过

    /**
     * 判断跳转到哪个Activity
     */
    public void judgeJumpActivity(){
        isFirstIn=ProjectSPUtils.getIsFirstLogin();
        isLogin=ProjectSPUtils.getIsLogin(false);

//        if (!isFirstIn){
//            ProjectSPUtils.setIsFirstLogin(true);
//            getView().goGuideActivity();
//            return;
//        }
//        if (isFirstIn){
            if (!isLogin){
                getView().goLoginActivity();
            }else {
                getView().goMainActivity();
            }
//        }
    }

}
