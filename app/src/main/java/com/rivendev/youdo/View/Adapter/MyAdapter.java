package com.rivendev.youdo.View.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rivendev.youdo.Model.DTO.Task;
import com.rivendev.youdo.R;
import com.rivendev.utils.DataUtils;

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
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);



        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        final Task task = tasks.get(position);
        String fDate = DataUtils.dateToString(task.getDate());

        holder.name.setText(task.getName());
        holder.description.setText(task.getDescription());
        holder.date.setText(fDate);

        if(task.getStat()){


            holder.btnCheck.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            holder.btnEdit.setColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_IN);
            holder.btnEdit.setEnabled(false);

        }else{

            holder.btnCheck.setColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_IN);

        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.taskDeleteClicked(task);

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.taskEditClicked(task);

            }
        });

        holder.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.taskExecutedClicked(task);

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
        @BindView(R.id.btn_check)
        ImageView btnCheck;




        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }

    }

    public interface TaskListener{

        void taskEditClicked(Task task);
        void taskDeleteClicked(Task task);
        void taskExecutedClicked(Task task);

    }


}

