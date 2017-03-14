package com.example.francis.examinationsystem.global;

import android.app.Application;
import android.content.Context;

import com.example.francis.examinationsystem.model.dao.DaoMaster;
import com.example.francis.examinationsystem.model.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Francis on 2017-3-10.
 */

public class App extends Application {
    public static Context mContext;

    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;

        initDB();
    }

    /**
     * 初始化数据库
     */
    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DBConstants.ENCRYPTED ?
                Constants.DBConstants.DB_NAME_ENCRYPTED : Constants.DBConstants.DB_NAME);
        Database db = Constants.DBConstants.ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
