package com.example.framework.lista.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.framework.lista.R;

import java.util.Timer;
import java.util.TimerTask;


public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        }, 2000);
    }

    private void goMain() {
        Intent intent = new Intent(LauncherActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }
}
