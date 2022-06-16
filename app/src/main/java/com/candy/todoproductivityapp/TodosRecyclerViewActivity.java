package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import uber.candy.todo.model.TodoModel;

public class TodosRecyclerViewActivity extends AppCompatActivity {

    RecyclerView todosRecyclerView;
    private TodosRecyclerViewAdapter adapter;

    Button btnAddTodos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_recycler_view);

        todosRecyclerView = findViewById(R.id.rv_todo_list_items);

        // Data
        List<TodoModel> todoList = new ArrayList<>();

        // Adapter
        adapter = new TodosRecyclerViewAdapter(todoList, this);
        todosRecyclerView.setHasFixedSize(true);
        todosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todosRecyclerView.setAdapter(adapter);

        btnAddTodos = findViewById(R.id.btn_add_todo);
        btnAddTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constructEditIntent();
            }


            private void constructEditIntent() {
                Intent intent = new Intent(TodosRecyclerViewActivity.this, AddOrUpdateTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}