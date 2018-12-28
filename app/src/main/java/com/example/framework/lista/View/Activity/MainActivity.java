package com.example.framework.lista.View.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.Presenter.MainPresenter;
import com.example.framework.lista.R;
import com.example.framework.lista.Services.AlarmReceiver;
import com.example.framework.lista.View.Adapter.MyAdapter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainPresenter.MainContract, MyAdapter.TaskListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView dRecyclerView;
    private RecyclerView.Adapter dAdapter;
    private RecyclerView.LayoutManager dLayoutManager;
    private MainPresenter presenter;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.title_my_recycler)
    TextView titleToDo;
    @BindView(R.id.title_done_recycler)
    TextView titleDone;

//    @BindView(R.id.test_btn)
//    Button testBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setAlarm();
        //  init();

        //pressTestButton();

    }

//    public void setDefaultToolbar(){
//
//
//        setSupportActionBar(toolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }

    public void setAlarm() {

        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (10000), pendingIntent);

    }


//
//    private void pressTestButton(){
//
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                taskNotification();
//
//            }
//        });
//
//    }


    private void taskNotification() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 31);
        calendar.set(Calendar.SECOND, 1);


    }


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

        AlertDialog alert = builder1.create();
        alert.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);


        alert.show();


    }


    private void init() {


//        setDefaultToolbar();
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.getListToDo();
        presenter.getListExecuted();


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
        presenter.getListToDo();
        presenter.getListExecuted();
        super.onRestart();


    }


    private void initRecycleToDo(List<Task> tasks) {

        mRecyclerView = findViewById(R.id.my_recycler_view);


        if (tasks.size() == 0) {

            titleToDo.setVisibility(View.INVISIBLE);

        } else {

            titleToDo.setVisibility(View.VISIBLE);
        }

        //FALAR COM O PAULO SOBRE O PREFETCH INITIAL SIZE QUE ESTÁ COM VALOR 2 (Layout Manager)


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        // ((LinearLayoutManager) mLayoutManager).setInitialPrefetchItemCount(tasks.size());


        mAdapter = new MyAdapter(tasks, this);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initRecycleDone(List<Task> tasks) {

        if (tasks.size() == 0) {

            titleDone.setVisibility(View.INVISIBLE);

        } else {

            titleDone.setVisibility(View.VISIBLE);
        }

        dRecyclerView = findViewById(R.id.done_recycler_view);

        dLayoutManager = new LinearLayoutManager(this);

        dRecyclerView.setLayoutManager(dLayoutManager);
        dRecyclerView.setHasFixedSize(true);


        dAdapter = new MyAdapter(tasks, this);
        dRecyclerView.setAdapter(dAdapter);


    }


    @Override
    public void listLoaded(List<Task> tasks, boolean stat) {


        if (stat) {
            initRecycleDone(tasks);
        } else {
            initRecycleToDo(tasks);
        }
    }

    @Override
    public void taskSaved() {

        presenter.getListExecuted();
        presenter.getListToDo();


    }

    @Override
    public void responseError(String error) {

        showError(error);


    }

    private void showError(String error) {
        // Snackbar snackbar = Snackbar.make(mRecyclerView, error, 2000);
        //snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void taskDeleted() {

        presenter.getListExecuted();
        presenter.getListToDo();


    }


    @Override
    public void taskEditClicked(Task task) {

        goToAddEdit(task);


    }

    @Override
    public void taskDeleteClicked(Task task) {


        initDialog(task);


    }

    @Override
    public void taskExecutedClicked(Task task) {

        task.setStat(true);
        presenter.saveTask(task);

    }
}