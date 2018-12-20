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

import butterknife.BindView;
import butterknife.ButterKnife;






public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Task> tasks;
    private Context context;
    private TaskListener listener;

    public MyAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
        this.listener = (TaskListener) context;
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

        final Task task = tasks.get(position);
        holder.name.setText(task.getName());
        holder.description.setText(task.getDescription());
        holder.date.setText(task.getDate().toString());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.taskDeleteClicked(task);

            }
        });



    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.btn_edit)
        ImageView btnEdit;
        @BindView(R.id.btn_delete)
        ImageView btnDelete;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }

    }

    public interface TaskListener{

        void taskEditClicked(Task task);
        void taskDeleteClicked(Task task);

    }


}

