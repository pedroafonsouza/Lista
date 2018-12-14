package com.example.framework.lista.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.R;
import com.example.framework.lista.View.Adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }


        private void init(){

            mRecyclerView = findViewById(R.id.my_recycler_view);

            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(new Task("tarefa1","nfnjn jfrnjnrj njDIDI WDIWJDIDWI DIJI jidjidwji jiwdj idwj iwdj wio wfo jwfoj o jfojwowjfo jwo jfwojfo wrfnjrn jrj", new Date()));
            tasks.add(new Task("tarefa2","nfnjn jfrnjnrj njrfnjrn jrj", new Date()));
            tasks.add(new Task("tarefa3","nfnjn jfrnjnrj njrfnjrn jrj", new Date()));
            tasks.add(new Task("tarefa4","nfnjn jfrnjnrWI WDIW JDIW IWD JIW DJII    JI JI IDWIDJI JW  WDIJWDIJWDIJDWI J WDIDJWIJW I DWJDI JDWj njrfnjrn jrj", new Date()));
            tasks.add(new Task("tarefa5","nfnjn jfrnjnrj njrfnjrn jrj", new Date()));

            mRecyclerView.setHasFixedSize(true);


            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);


            mAdapter = new MyAdapter(tasks, this);
            mRecyclerView.setAdapter(mAdapter);

        }


    }