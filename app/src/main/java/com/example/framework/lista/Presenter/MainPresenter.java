package com.example.framework.lista.Presenter;


import com.example.framework.lista.Model.Business.TaskBusiness;
import com.example.framework.lista.Model.DTO.Task;

import java.util.ArrayList;
import java.util.List;


public class MainPresenter {




    public interface MainContract {

        void listLoaded(List<Task> tasks);

        void listError(String error);

        void taskDeleted();

        void taskDeletedError(String error);


    }

    private TaskBusiness taskBusiness;
    private MainContract contract;

    public MainPresenter() {

        taskBusiness = new TaskBusiness();

    }

    public void setView(MainContract contract) {

        this.contract = contract;

    }

    public void getList() {

        try {

            //sucess
            List<Task> tasks = taskBusiness.getList();
            if (tasks == null) throw new Exception();
            contract.listLoaded(tasks);
        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.listError("Fail on load list");

        }

    }

    public void removeTask(Task task) {

        try {
            //sucess

            if (taskBusiness.removeTask(task)) {
                contract.taskDeleted();
            } else {
                contract.taskDeletedError("Fail on delete task");
            }

        } catch (Exception e) {
            //error
            e.printStackTrace();
            contract.taskDeletedError("Fail on delete task");
        }

    }




}
