package com.rivendev.youdo.Presenter;

import com.rivendev.youdo.Model.Business.TaskBusiness;
import com.rivendev.youdo.Model.DTO.Task;

public class TaskFormPresenter {


    public interface TaskFormContract {

        void taskEditedAdded();

        void taskError(String error);


    }


    private TaskBusiness taskBusiness;
    private TaskFormContract contract;

    public TaskFormPresenter() {
        taskBusiness = new TaskBusiness();
        this.contract = contract;
    }

    public void setView(TaskFormContract contract) {

        this.contract = contract;

    }

    public boolean validateField(Task task) {

        if (task.getName().isEmpty() || task.getDescription().isEmpty()){

            return false;
        } else {

            return true;
        }


    }

    public void addEditTask(Task task) {

        if (validateField(task)) {

            try {
                //sucess

                if (taskBusiness.addEditTask(task)) {
                    contract.taskEditedAdded();
                } else {
                    contract.taskError("Fail on save task");
                }

            } catch (Exception e) {
                //error
                e.printStackTrace();
                contract.taskError("Fail on save task");
            }
        } else {
            contract.taskError("Fill the fields correctly");

        }
    }

}

