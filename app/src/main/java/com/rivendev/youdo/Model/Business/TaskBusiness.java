package com.rivendev.youdo.Model.Business;

import com.rivendev.youdo.Model.DAO.TaskDAO;
import com.rivendev.youdo.Model.DTO.Task;
import com.rivendev.utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Task> getListToDo() {

        List<Task> tasks = new ArrayList<>();
        tasks = taskDAO.getToDo(false);
        return tasks;

    }

    public List<Task> getListExecuted(){
        List<Task> tasks = new ArrayList<>();
        tasks = taskDAO.getToDo(true);

        return tasks;
    }




    public boolean addEditTask(Task task){

        try {
            taskDAO.newEditTask(task);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }


    }

    public boolean removeTask(Task task){


            try {
                taskDAO.delete(task);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }


    }




    public List getId(){

        return null;
    }





}

