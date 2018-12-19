package com.example.framework.lista.View.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.Presenter.TaskFormPresenter;
import com.example.framework.lista.R;

import java.text.SimpleDateFormat;
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
    Calendar cal = Calendar.getInstance();





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


    }

    private void init() {

        presenter = new TaskFormPresenter();
        presenter.setView(this);


    }

    @OnClick(R.id.date_form)
    protected void getDate(){



            int year =  cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {


                cal.set(year, month, day);
                date = cal.getTime();

                Log.d(TAG, "onDateSet: dd/mm/yyyy" + day + "/" + month +"/" + year);

                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
                String dates = fmt.format(date);

                dateTime.setText(dates);



            }


        };


        DatePickerDialog dialog = new DatePickerDialog(TaskFormActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                    mDateSetListener,
                    year, month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();






    }

    @OnClick(R.id.bt_confirm)
    protected void saveTask(){



        String name = taskName.getText().toString();
        String descript = taskDescript.getText().toString();



        Task task = new Task();

        task.setName(name);
        task.setDescription(descript);

        task.setDate(date);


        presenter.addTask(task);
    }

    @Override
    public void taskAdded() {

        Toast.makeText(TaskFormActivity.this, "Task added with sucess", Toast.LENGTH_LONG).show();


    }

    @Override
    public void taskError(String error) {

        Toast.makeText(TaskFormActivity.this, error, Toast.LENGTH_LONG).show();


    }

}
