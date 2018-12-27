package com.example.framework.lista.View.Activity;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.Presenter.TaskFormPresenter;
import com.example.framework.lista.R;
import com.example.framework.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskFormActivity extends AppCompatActivity implements TaskFormPresenter.TaskFormContract {


    private TaskFormPresenter presenter;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "TaskFormAcitvity";
    private Date date = new Date();


    private String dates = DataUtils.dateToString(date);
    Calendar cal = Calendar.getInstance();
    Task task = null;


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
        ButterKnife.bind(this);
        init();
        initForm();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    private void taskNotify() {


        Intent goMain = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, goMain, 0);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            Notification notify = new NotificationCompat.Builder
                    (getApplicationContext())
                    .setSound(alarmSound)
                    .setVibrate(new long[]{300, 700, 1000})
                    .setContentTitle("Task added with sucess")
                    .setContentIntent(pendingIntent)
                    .setContentText(task.getName()).
                            setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(task.getDescription()))
                    .setSmallIcon(R.drawable.ic_launcher_coral).build();

            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notif.notify(0, notify);

    }


    private void initForm() {

        if (task != null) {
            taskName.setText(task.getName());
            taskDescript.setText(task.getDescription());
            String dt = DataUtils.dateToString(task.getDate());
            dateTime.setText(dt);


        } else {
            task = new Task();
        }

    }


    private void init() {

        presenter = new TaskFormPresenter();
        presenter.setView(this);


        try {
            task = (Task) getIntent().getSerializableExtra("task");

        } catch (Exception e) {

            Log.e("TASK NULL", e.getMessage());
        }
    }


    private void initTimeDialog() {

        final int shour = cal.get(Calendar.HOUR_OF_DAY);
        final int smin = cal.get(Calendar.MINUTE);


        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {


                    @Override
                    public void onTimeSet(TimePicker view, int hour,
                                          int min) {


                        date = DataUtils.setHourMinCurrentDate(date, hour, min);
                        dates = DataUtils.dateToString(date, "dd/MM/yyyy - HH:mm");
                        dateTime.setText(dates);

                    }
                }, shour, smin, true);

        timePickerDialog.show();


    }

    @OnClick(R.id.date_form)
    protected void getDate() {

        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {


                date = DataUtils.setCurrentDate(year, month, day);
                initTimeDialog();


            }


        };


        DatePickerDialog dialog = new DatePickerDialog(TaskFormActivity.this,
                mDateSetListener,
                year, month, day);
        dialog.getDatePicker().setMinDate(cal.getTime().getTime());
        dialog.show();


    }

    @OnClick(R.id.bt_confirm)
    protected void saveTask() {


        String name = taskName.getText().toString();
        String descript = taskDescript.getText().toString();
        task.setName(name);
        task.setDescription(descript);
        task.setDate(date);
        task.setStat(false);

        presenter.addEditTask(task);
        taskNotify();
    }

    @Override
    public void taskEditedAdded() {

        finish();


    }

    @Override
    public void taskError(String error) {

        Toast.makeText(TaskFormActivity.this, error, Toast.LENGTH_LONG).show();


    }

}
