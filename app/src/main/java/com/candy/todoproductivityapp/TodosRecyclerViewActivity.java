package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uber.candy.todo.model.TodoModel;

public class TodosRecyclerViewActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "userPrefs";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";

    Button btnAdd;
    Button btnLogOut;


    TextView tvDisplayUserName;
    TextView tvDisplayUserEmail;
    private TodoHelper toDoHelper;

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


        toDoHelper = new TodoHelper(getApplicationContext());
        toDoHelper.getFromDisk();

        btnAdd = findViewById(R.id.btn_add_todo);
        btnAdd.setOnClickListener(v -> addNewTodo());

        RecyclerView todosRecyclerView = findViewById(R.id.rv_todo_list_items);
        todosRecyclerView.setHasFixedSize(true);

        Toast.makeText(this,"Todo Count: " + toDoHelper.latestList.size(), Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        todosRecyclerView.setLayoutManager(layoutManager);

        toDoHelper.adapter = new TodosRecyclerViewAdapter(toDoHelper.latestList, this);
        todosRecyclerView.setAdapter(toDoHelper.adapter);

        AddOrUpdateTodoActivity.todoHelper = toDoHelper;
    }

    /**
     * This is requesting communication to navigate to the AddOrUpdateTodoActivity using the startActivity method
     */
    private void addNewTodo() {
        startActivity(AddOrUpdateTodoActivity.constructCreateIntent(this));
    }

    /**
     * To refresh the list, you must know a timing to refresh it.
     * Or we should you have to know when you could refresh it.
     * In Android, we have activity lifecycle. And onResume is the one that will always be called
     * when your activity is visible.
     *
     *
     * Once you know how to refresh it, then you need to get the latest data.
     * That is the reason why we fetch it from the disk again.
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        List<TodoModel> newList = toDoHelper.fetchFromDisk();
        toDoHelper.adapter.refreshData(newList);
    }
}