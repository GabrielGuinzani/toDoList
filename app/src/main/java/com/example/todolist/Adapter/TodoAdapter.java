package com.example.todolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.model.Todo;
import com.example.todolist.TodoDatabaseHelper;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private List<Todo> todoList;
    private TodoDatabaseHelper db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageButton deleteButton;

        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }

    public TodoAdapter(List<Todo> todoList, TodoDatabaseHelper db) {
        this.todoList = todoList;
        this.db = db;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.title.setText(todo.getTask());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteTask(todo.getTodo_ID());
                todoList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, todoList.size());
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                db.updateStatus(todo.getTodo_ID(), isChecked ? 1 : 0);
            }
        });

        // Set the checkbox state based on the status from the database
        holder.checkBox.setChecked(todo.getStatus() == 1);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}