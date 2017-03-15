package com.example.francis.examinationsystem.util.sp;

import com.example.francis.examinationsystem.global.Constants;

/**
 * SP 的进一步封装
 * Created by Francis on 2017-3-15.
 */

public class ProjectSPUtils {
    /**
     * 登录状态
     * @param b
     */
    public static void setIsLogin(boolean b) {
        MaSPUtils.put( Constants.SharedPreferences.SF_KEY_LOGIN_STATUS, b);
    }

    public static boolean getIsLogin(boolean defaultValue){
        return (Boolean)MaSPUtils.get(Constants.SharedPreferences.SF_KEY_LOGIN_STATUS, defaultValue);
    }

    /**
     * 首次登录
     * @param b
     */
    public static void setIsFirstLogin(boolean b) {
        MaSPUtils.put(Constants.SharedPreferences.SF_KEY_FIRST_LOGIN, b);
    }

    public static boolean getIsFirstLogin(){
        return (Boolean)MaSPUtils.get(Constants.SharedPreferences.SF_KEY_FIRST_LOGIN, false);
    }
}
