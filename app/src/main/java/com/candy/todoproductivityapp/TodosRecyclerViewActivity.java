package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TodosRecyclerViewActivity extends AppCompatActivity {

    RecyclerView todosRecyclerView;

    Button btnAddTodos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_recycler_view);

        todosRecyclerView = findViewById(R.id.rv_todo_list_items);

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