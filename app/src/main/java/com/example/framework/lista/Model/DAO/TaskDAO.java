package com.example.framework.lista.Model.DAO;

import com.example.framework.lista.Model.DTO.Task;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO extends BaseDaoImpl<Task, Integer> {


    public TaskDAO(ConnectionSource cs) throws SQLException {
        super(Task.class);
        setConnectionSource(cs);
        initialize();
    }



    public List<Task> getAll(){
        try {
            return queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Task getById(Integer id){
        try {
            return getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deleteTask(Task task){
        try {
            return  delete(task);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public void newEditTask(Task task){
        try {
            createOrUpdate(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
