package com.example.framework.lista.View.Activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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
    private MainPresenter presenter;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

//    @BindView(R.id.test_btn)
//    Button testBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        init();
        setAlarm();
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
        AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 40);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC, AlarmManager.INTERVAL_HALF_DAY, AlarmManager.INTERVAL_HALF_DAY, pendingIntent);

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


    private void taskNotification(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 31);
        calendar.set(Calendar.SECOND, 1);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent goMain = new Intent(this, LauncherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, goMain, 0);

        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (getApplicationContext())
                .setSound(alarmSound)
                .setVibrate(new long[] {300,700,1000})
                .setContentTitle("TESTE")
                .setContentIntent(pendingIntent)
                .setContentText("Teste")

                .setSmallIcon(R.drawable.ic_launcher_coral).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);



    }

    private  void checkTask(){





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


        final ImageView btnCheck= findViewById(R.id.btn_check);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              btnCheck.getDrawable().s

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