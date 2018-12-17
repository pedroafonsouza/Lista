package com.example.framework.lista.Model.Business;

import com.example.framework.lista.Model.DAO.TaskDAO;
import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.View.Activity.MainActivity;
import com.example.framework.utils.DatabaseHelper;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.framework.utils.DatabaseHelper.databaseName;

public class TaskBusiness {


    private DatabaseHelper dh;
    private TaskDAO taskDAO;

    public List<Task> getList() {

        dh = new DatabaseHelper(TaskBusiness.this, databaseName);

        ArrayList<Task> tasks = new ArrayList<>();




        tasks.add(new Task("Tarefa 1", "Garantir a planificação periódica (trimestral e mensal) das actividades gerais do projecto A Nível provincial.", new Date()));
        tasks.add(new Task("Tarefa 2", "Apoiar a Gestão Provincial na orçamentação das actividades.", new Date()));
        tasks.add(new Task("Tarefa 3", "Elaborar relatórios mensais e trimestrais das actividades do projecto a nível provincial (Relatorio Quantitativo e Qualitativo).", new Date()));
        tasks.add(new Task("Tarefa 4", "Elaborar relatório trimestral do projecto (PUDR).", new Date()));
        tasks.add(new Task("Tarefa 5", "Garantir o cumprimento dos prazos de envio de relatórios mensais e trimestrais ao nível." +
                "central", new Date()));


        try {
            taskDAO = new TaskDAO(dh.getConnectionSource());

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }



    }


