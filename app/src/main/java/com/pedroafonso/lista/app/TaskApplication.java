package com.rivendev.youdo.app;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class TaskApplication extends Application {




    private static TaskApplication instance;

    public static TaskApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }




}


