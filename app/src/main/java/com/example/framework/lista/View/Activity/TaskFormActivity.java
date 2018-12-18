package com.example.framework.lista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.framework.lista.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskFormActivity extends AppCompatActivity {

    @BindView(R.id.task_name)
    EditText taskName;

    @BindView(R.id.task_descript)
    EditText taskDescript;

    @BindView(R.id.date_time)
    TextView dateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);


        TextView textView = findViewById(R.id.title_toolbar);
        String name = getIntent().getStringExtra("name");
        textView.setText(name);

    }

    @OnClick(R.id.bt_date)
    protected void getDate(){



    }

    @OnClick(R.id.bt_confirm)
    protected void saveTask(){

        String name = taskName.getText().toString();
        String descript = taskDescript.getText().toString();


    }


}
