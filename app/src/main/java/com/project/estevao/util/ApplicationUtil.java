package com.project.estevao.apigit.util;

import android.content.Context;

/**
 * Created by Estevao on 07/08/2016.
 */
public class ApplicationUtil {
    private static Context applicationContext;

    private ApplicationUtil() {
        super();
    }


    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static Context getContext() {
        return applicationContext;
    }
}
