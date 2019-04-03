package com.maple.rimaproject.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Khalid Aldaboubi on 4/3/2019 11:34 AM .
 * Maple Technologies Ltd
 * khalid.aldaboubi93@gmail.com
 * Project Name : royalev-app
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
