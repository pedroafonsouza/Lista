package com.example.framework.lista.View.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainPresenter.MainContract, MyAdapter.TaskListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainPresenter presenter;

    @BindView(R.id.btn_edit)
    ImageView editTask;

    @BindView(R.id.btn_delete)
    ImageView deleteTask;





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

        ImageView btnAddTask = findViewById(R.id.btn_add);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TaskFormActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onRestart() {
        presenter.getList();
        super.onRestart();

    }



    private void initRecycle(List<Task> tasks) {

        mRecyclerView = findViewById(R.id.my_recycler_view);



        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new MyAdapter(tasks, this);
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    public void listLoaded(List<Task> tasks) {

        initRecycle(tasks);

    }

    @Override
    public void listError(String error) {

        showError(error);


    }

    private void showError(String error) {
        Snackbar snackbar = Snackbar.make(mRecyclerView, error, 2000);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void taskDeleted() {

        presenter.getList();

    }

    @Override
    public void taskDeletedError(String error) {

        showError(error);

    }

    @Override
    public void taskEditClicked(Task task) {

    }

    @Override
    public void taskDeleteClicked(Task task) {

      presenter.removeTask(task);

    }
}