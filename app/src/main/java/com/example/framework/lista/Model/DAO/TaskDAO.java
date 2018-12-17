package com.example.framework.lista.Model.DAO;

import com.example.framework.lista.Model.DTO.Task;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class TaskDAO extends BaseDaoImpl<Task, Integer> {


    protected TaskDAO(ConnectionSource cs) throws SQLException {
        super(Task.class);
        setConnectionSource(cs);
        initialize();
    }
}
