package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todolist.db";
    private static final int DATABASE_VERSION = 1;

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TODO = "CREATE TABLE IF NOT EXISTS " + Todo.TABLE  + "("
                + Todo.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Todo.KEY_task + " TEXT, "
                + Todo.KEY_status + " INTEGER )";
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Todo.TABLE);
        onCreate(db);
    }

    public void insertTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Todo.KEY_task, task);
        values.put(Todo.KEY_status, 0); // assuming 0 is for not done

        db.insert(Todo.TABLE, null, values);
        db.close();
    }

    public List<Todo> getAllTodos() {
        List<Todo> todoList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Todo.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo();
                todo.todo_ID = cursor.getInt(cursor.getColumnIndex(Todo.KEY_ID));
                todo.task = cursor.getString(cursor.getColumnIndex(Todo.KEY_task));
                todo.status = cursor.getInt(cursor.getColumnIndex(Todo.KEY_status));

                todoList.add(todo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return todoList;
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Todo.TABLE, Todo.KEY_ID + " = ?", new String[] { String.valueOf(id)});
        db.close();
    }

    public void updateStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Todo.KEY_status, status);

        db.update(Todo.TABLE, values, Todo.KEY_ID + " = ?", new String[] { String.valueOf(id)});
        db.close();
    }
}