package com.example.framework.lista.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.lista.Model.DTO.Task;
import com.example.framework.lista.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Task> tasks;
    private Context context;

    public MyAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.name.setText(task.getName());
        holder.description.setText(task.getDescription());
        holder.date.setText(task.getDate().toString());

    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public TextView date;
        public ImageView btnEdit;
        public ImageView btnDelete;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            description = v.findViewById(R.id.description);
            date = v.findViewById(R.id.date);
            btnEdit = v.findViewById(R.id.btn_edit);
            btnDelete = v.findViewById(R.id.btn_delete);
        }

    }

}

