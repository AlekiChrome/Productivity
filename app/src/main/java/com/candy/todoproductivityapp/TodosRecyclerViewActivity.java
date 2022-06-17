package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import uber.candy.todo.model.TodoModel;
import view.holder.TodoViewHolder;

public class TodosRecyclerViewActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "userPrefs";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";

    Button btnAdd;
    Button btnLogOut;


    TextView tvDisplayUserName;
    TextView tvDisplayUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_recycler_view);

        tvDisplayUserName = findViewById(R.id.tv_full_name);
        tvDisplayUserEmail = findViewById(R.id.tv_full_email);

        btnLogOut = findViewById(R.id.btn_logout);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        String user = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (user != null || email != null) {
            tvDisplayUserName.setText("" + user);
            tvDisplayUserEmail.setText("" + email);
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.commit();
                finish();

                Toast.makeText(TodosRecyclerViewActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
            }
        });



        TodoHelper toDoHelper = new TodoHelper(getApplicationContext());
        toDoHelper.getFromDisk();

        btnAdd = findViewById(R.id.btn_add_todo);
        btnAdd.setOnClickListener(v -> addNewTodo());

        RecyclerView todosRecyclerView = findViewById(R.id.rv_todo_list_items);
        todosRecyclerView.setHasFixedSize(true);

        Toast.makeText(this,"Todo Count: " + toDoHelper.list.size(), Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        todosRecyclerView.setLayoutManager(layoutManager);

        toDoHelper.adapter = new TodosRecyclerViewAdapter(toDoHelper.list, this);
        todosRecyclerView.setAdapter(toDoHelper.adapter);

        AddOrUpdateTodoActivity.todoHelper = toDoHelper;
    }

    /**
     * This is requesting communication to navigate to the AddOrUpdateTodoActivity using the startActivity method
     */
    private void addNewTodo() {
        startActivity(AddOrUpdateTodoActivity.constructCreateIntent(this));
    }

}