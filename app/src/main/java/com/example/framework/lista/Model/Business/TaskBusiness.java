package com.example.framework.lista.Model.Business;

import com.example.framework.lista.Model.DAO.TaskDAO;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

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

    public boolean addTask(Task task){

        try {
            taskDAO.newTask(task);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }


    }



}

