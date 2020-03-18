package com.knewbie.news.global;

import android.app.Application;

import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;

public class GlobalApplication extends Application {
    private DatabaseOperationDao databaseOperationDao;
    private UserBean userBean;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseOperationDao = new DatabaseOperationDao(this);
    }

    public DatabaseOperationDao getDatabaseOperationDao() {
        return databaseOperationDao;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }
}
