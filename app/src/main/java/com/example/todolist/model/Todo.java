package com.example.todolist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Todo {
    public static final String TABLE = "Todo";

    public static final String KEY_ID = "id";
    public static final String KEY_task = "task";
    public static final String KEY_status = "status";

    public int todo_ID;
    public String task;
    public int status;

    public String getTask() {
        return this.task;
    }

    public int getTodo_ID() {
        return todo_ID;
    }

    public void setTodo_ID(int todo_ID) {
        this.todo_ID = todo_ID;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}