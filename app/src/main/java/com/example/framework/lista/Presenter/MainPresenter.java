package com.example.framework.lista.Presenter;


import com.example.framework.lista.Model.Business.TaskBusiness;
import com.example.framework.lista.Model.DTO.Task;

import java.util.List;


public class MainPresenter {




    public interface MainContract {

        void listLoaded(List<Task> tasks, boolean stat);


        void taskSaved();

        void responseError(String error);


        void taskDeleted();




    }

    private TaskBusiness taskBusiness;
    private MainContract contract;

    public MainPresenter() {

        taskBusiness = new TaskBusiness();

    }

    public void setView(MainContract contract) {

        this.contract = contract;

    }


    public void getListToDo() {

        try {

            //sucess


            List<Task> tasks = taskBusiness.getListToDo();
            if (tasks == null) throw new Exception();
            contract.listLoaded(tasks, false);
        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.responseError("Fail on load list");

        }

    }

    public void getListExecuted() {

        try {

            //sucess


            List<Task> tasks = taskBusiness.getListExecuted();
            if (tasks == null) throw new Exception();
            contract.listLoaded(tasks, true);
        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.responseError("Fail on load list");

        }

    }



    public void saveTask(Task task) {


        try {
            //sucess

            if (taskBusiness.addEditTask(task)) {
                contract.taskSaved();
            } else {
                contract.responseError("Fail on delete task");
            }

        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.responseError("Fail on delete task");
        }

    }

    public void removeTask(Task task) {

        try {
            //sucess

            if (taskBusiness.removeTask(task)) {
                contract.taskDeleted();
            } else {
                contract.responseError("Fail on delete task");
            }

        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.responseError("Fail on delete task");
        }

    }




}
