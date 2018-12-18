package com.example.framework.lista.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.framework.lista.Model.DTO.Task;

import java.util.List;

public class TaskApplication extends Application {




    private static TaskApplication instance;

    public static TaskApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }




}


