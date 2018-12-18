package com.example.framework.lista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.framework.lista.Model.DAO.TaskDAO;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.Presenter.MainPresenter;
import com.example.framework.lista.R;
import com.example.framework.lista.View.Adapter.MyAdapter;
import com.example.framework.utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainContract{




    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



    }


    private void init() {

        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.getList();

    }

    private void initRecycle(List<Task> tasks) {

        mRecyclerView = findViewById(R.id.my_recycler_view);
        ImageView btnAddTask = findViewById(R.id.btn_add);


        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new MyAdapter(tasks, this);
        mRecyclerView.setAdapter(mAdapter);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TaskFormActivity.class);
                intent.putExtra("name", "Add task name");
                intent.putExtra("description", "Add a task description here...");
                startActivity(intent);

            }
        });

    }




    @Override
    public void listLoaded(List<Task> tasks) {

        initRecycle(tasks);

    }

    @Override
    public void listError(String error) {

        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();


    }
}