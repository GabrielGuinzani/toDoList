package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.TodoDatabaseHelper;

public class AddTaskActivity extends AppCompatActivity {

    private TodoDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db = new TodoDatabaseHelper(this);
    }

    public void addTask(View v) {
        EditText taskEditText = (EditText) findViewById(R.id.taskEditText);
        String task = taskEditText.getText().toString();
        db.insertTask(task);
        finish();
    }
}