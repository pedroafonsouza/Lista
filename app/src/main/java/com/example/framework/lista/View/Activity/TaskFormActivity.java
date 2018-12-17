package com.example.framework.lista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.framework.lista.R;

public class TaskFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);


        TextView textView = findViewById(R.id.title_toolbar);
        String name = getIntent().getStringExtra("name");
        textView.setText(name);



    }
}
