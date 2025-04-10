package com.gshoaib998.moneytracker;

import android.app.Application;

import com.gshoaib998.moneytracker.data.database.ObjectBox;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
