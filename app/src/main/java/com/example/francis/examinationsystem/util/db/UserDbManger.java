package com.example.francis.examinationsystem.util.db;

import com.example.francis.examinationsystem.entity.User;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Francis on 2017-3-16.
 */

public class UserDbManger extends AbstractDatabaseManager<User,String> {
    @Override
    AbstractDao getAbstractDao() {
        return daoSession.getUserDao();
    }
}
