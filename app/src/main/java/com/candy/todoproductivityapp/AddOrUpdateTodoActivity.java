package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import uber.candy.todo.model.TodoModel;

public class AddOrUpdateTodoActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;

    Button btnSave;
    Button btnCancel;

    List<TodoModel> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_todo);

        initView();
    }

    private void initView() {
        etTitle = findViewById(R.id.et_todo_title);
        etContent = findViewById(R.id.et_todo_content);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> save());
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(view -> finish());
    }

    private void save() {

    }

    private void createTodo() {

    }

}