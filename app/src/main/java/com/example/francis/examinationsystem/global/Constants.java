package com.example.francis.examinationsystem.global;

/**
 * Created by Francis on 2017-3-10.
 * 常量
 */

public class Constants {
    /**
     * 数据库常量
     */
    public static class DBConstants{
        //数据库是否加密
        public static final boolean ENCRYPTED = false;
        public static final String DB_NAME = "examination-db";
        public static final String DB_NAME_ENCRYPTED = "examination-db-encrypted";
    }

    // SharedPreferences相关常量
    public static class SharedPreferences {
        public static final String SharedPreferencesName = Project.ProjectName
                + ".sp";
        public static final String XxxKey = "key";

        public static final String SF_KEY_LOGIN_STATUS = "login";// 已登录状态
        public static final String SF_KEY_FIRST_LOGIN = "first_login";

    }

    // 工程相关常量
    public static class Project {
        public static final String ProjectName = "ES";
    }


}
