package com.example.francis.examinationsystem.global;

import android.os.Environment;

import java.io.File;

/**
 * Created by Francis on 2017-3-10.
 * 常量
 */

public class Constants {
    /**
     * 数据库常量
     */
    public static class DBConstants {
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
        public static final String app_id = "2be17ab7f2deac4e1ca2424694da4c8f";
        public static final String rest_api_key = "e853de15abe33486b7eadfe5be5f29e5";
        /**
         * 一般网络请求超时时间
         */
        public static final long networkTimeout = 10;
        public static final String baseUrl="https://api.bmob.cn/1/classes/";

    }


    public static class MainDrawerType {
        //主界面打开的类型
        public final static int COURSE = 1;
        public final static int MESSAGE = 2;
        public final static int CONTACTS = 3;
    }


    public static class Fold {
        public final static String MAIN_FOLDER = "examination_system" + File.separator;
        public final static String DATA_FOLDER = "data" + File.separator;
        public static final String ALBUM_PATH = Environment.getExternalStorageDirectory() + File.separator + MAIN_FOLDER + File.separator;
        public final static String PHOTO_FOLDER = ALBUM_PATH + "photo" + File.separator;
    }

    //题目类型
    public static class ExamType{
        public final static int EXAM_JUDGE=0;
        public final static int EXAM_MUTIPLE=1;
        public final static int EXAM_SHORT=2;
        public final static int EXAM_SINGLE=3;
    }

}
