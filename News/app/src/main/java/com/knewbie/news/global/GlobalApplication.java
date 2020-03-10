package com.knewbie.news.global;

import android.app.Application;

import com.knewbie.news.db.DatabaseOperationDao;

public class GlobalApplication extends Application {
    private DatabaseOperationDao databaseOperationDao;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseOperationDao = new DatabaseOperationDao(this);
    }

    public DatabaseOperationDao getDatabaseOperationDao() {
        return databaseOperationDao;
    }
}
