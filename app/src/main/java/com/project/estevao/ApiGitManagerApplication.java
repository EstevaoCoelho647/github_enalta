package com.project.estevao.apigit;

import android.app.Application;

import com.project.estevao.apigit.util.ApplicationUtil;

/**
 * Created by Estevao on 07/08/2016.
 */
public class ApiGitManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
