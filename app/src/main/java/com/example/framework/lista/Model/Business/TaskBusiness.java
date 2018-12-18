package com.example.framework.lista.Model.Business;

import com.example.framework.lista.Model.DAO.TaskDAO;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.View.Activity.MainActivity;
import com.example.framework.utils.DatabaseHelper;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.example.framework.utils.DatabaseHelper.databaseName;

public class TaskBusiness {


    private DatabaseHelper dh;
    private TaskDAO taskDAO;


    public TaskBusiness() {

        dh = new DatabaseHelper();
        try {
            taskDAO = new TaskDAO(dh.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Task> getList() {

        List<Task> tasks = new ArrayList<>();
        tasks = taskDAO.getAll();
        return tasks;

    }


}

