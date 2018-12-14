package com.example.framework.lista.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.R;
import com.example.framework.lista.View.Adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }


        private void init(){

            mRecyclerView = findViewById(R.id.my_recycler_view);
            ImageView btnAddTask = findViewById(R.id.btn_add);

            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(new Task("Tarefa 1","Garantir a planificação periódica (trimestral e mensal) das actividades gerais do projecto A Nível provincial.", new Date()));
            tasks.add(new Task("Tarefa 2","Apoiar a Gestão Provincial na orçamentação das actividades.", new Date()));
            tasks.add(new Task("Tarefa 3","Elaborar relatórios mensais e trimestrais das actividades do projecto a nível provincial (Relatorio Quantitativo e Qualitativo).", new Date()));
            tasks.add(new Task("Tarefa 4","Elaborar relatório trimestral do projecto (PUDR).", new Date()));
            tasks.add(new Task("Tarefa 5","Garantir o cumprimento dos prazos de envio de relatórios mensais e trimestrais ao nível." +
                    "central", new Date()));

            mRecyclerView.setHasFixedSize(true);


            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);


            mAdapter = new MyAdapter(tasks, this);
            mRecyclerView.setAdapter(mAdapter);
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, TaskFormActivity.class);
                    intent.putExtra("name", "Pedro Afonso");
                    startActivity(intent);

                }
            });

        }



    }