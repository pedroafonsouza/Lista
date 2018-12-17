package com.example.framework.lista.Model.Business;

import com.example.framework.lista.Model.DTO.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskBusiness {

    public List<Task> getList() {


        ArrayList<Task> tasks = new ArrayList<>();
        
        try
        {
            throw new NullPointerException("demo");

        }
        catch(NullPointerException e)
        {
            System.out.println("Caught inside fun().");
            throw e; // rethrowing the exception
        }

/*        throw new Exception("Teste");
        tasks.add(new Task("Tarefa 1", "Garantir a planificação periódica (trimestral e mensal) das actividades gerais do projecto A Nível provincial.", new Date()));
        tasks.add(new Task("Tarefa 2", "Apoiar a Gestão Provincial na orçamentação das actividades.", new Date()));
        tasks.add(new Task("Tarefa 3", "Elaborar relatórios mensais e trimestrais das actividades do projecto a nível provincial (Relatorio Quantitativo e Qualitativo).", new Date()));
        tasks.add(new Task("Tarefa 4", "Elaborar relatório trimestral do projecto (PUDR).", new Date()));
        tasks.add(new Task("Tarefa 5", "Garantir o cumprimento dos prazos de envio de relatórios mensais e trimestrais ao nível." +
                "central", new Date()));
*/

        return tasks;

    }

}
