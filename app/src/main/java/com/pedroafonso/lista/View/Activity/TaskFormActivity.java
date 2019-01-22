package com.pedroafonso.lista.View.Activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pedroafonso.lista.Model.DTO.Task;
import com.pedroafonso.lista.Presenter.TaskFormPresenter;
import com.pedroafonso.lista.R;
import com.pedroafonso.lista.Services.AlarmReceiver;
import com.pedroafonso.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskFormActivity extends AppCompatActivity implements TaskFormPresenter.TaskFormContract {


    private TaskFormPresenter presenter;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "TaskFormAcitvity";
    private String note1 = "";
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

    public void setAlarm(Task task) {

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("Task", task);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), task.getId(), intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, task.getDate().getTime() - 60000, pendingIntent);


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
        setAlarm(task);
        Toast.makeText(this, "Task added with sucess", Toast.LENGTH_LONG).show();
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
