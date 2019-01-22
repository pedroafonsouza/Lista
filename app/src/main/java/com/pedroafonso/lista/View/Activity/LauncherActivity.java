package com.pedroafonso.lista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.pedroafonso.lista.R;


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
