package com.example.framework.lista.View.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.framework.lista.Model.DAO.TaskDAO;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.Presenter.MainPresenter;
import com.example.framework.lista.R;
import com.example.framework.lista.View.Adapter.MyAdapter;
import com.example.framework.utils.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.framework.lista.R.color.colorPrimaryDark;
import static com.example.framework.lista.R.color.white;


public class MainActivity extends AppCompatActivity implements MainPresenter.MainContract, MyAdapter.TaskListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainPresenter presenter;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

    }

//    public void setDefaultToolbar(){
//
//
//        setSupportActionBar(toolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }





    private void goToAddEdit(Task task) {

        Intent intent = new Intent(MainActivity.this, TaskFormActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);


    }


    private void initDialog(final Task task) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogF);
        builder1.setMessage("\n" +
                "Do you really want to delete your task?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes, delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.removeTask(task);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);



        alert11.show();


    }


    private void init() {


//        setDefaultToolbar();
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.getList();

        ImageView btnAddTask = findViewById(R.id.btn_add);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAddEdit(null);

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
        // Snackbar snackbar = Snackbar.make(mRecyclerView, error, 2000);
        //snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
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

        goToAddEdit(task);


    }

    @Override
    public void taskDeleteClicked(Task task) {


        initDialog(task);


    }
}