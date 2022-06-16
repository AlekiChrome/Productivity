package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uber.candy.todo.model.TodoModel;

public class TodosRecyclerViewActivity extends AppCompatActivity {

    private TodosRecyclerViewAdapter adapter;
    private TodoHelper toDoHelper;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_recycler_view);

        toDoHelper = new TodoHelper(getApplicationContext());
        List<TodoModel> list = toDoHelper.getFromDisk();

        btnAdd = findViewById(R.id.btn_add_todo);
        btnAdd.setOnClickListener(v -> addNewTodo());

        RecyclerView todosRecyclerView = findViewById(R.id.rv_todo_list_items);
        todosRecyclerView.setHasFixedSize(true);

        Toast.makeText(this,"Todo Count: " + list.size(), Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        todosRecyclerView.setLayoutManager(layoutManager);

        adapter = new TodosRecyclerViewAdapter(list, this);
        todosRecyclerView.setAdapter(adapter);


    }

    /**
     * This is requesting communication to navigate to the AddOrUpdateTodoActivity using the startActivity method
     */
    private void addNewTodo() {
        startActivity(AddOrUpdateTodoActivity.constructCreateIntent(this));
    }


}