package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import uber.candy.todo.model.TodoModel;
import view.holder.TodoViewHolder;

public class AddOrUpdateTodoActivity extends AppCompatActivity implements TodosRecyclerViewInterface {
    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_CONTENT = "extra_content";
    public static final int DEFAULT_ID = -1;


    EditText etTitle;
    EditText etContent;

    Button btnSaveTodo;
    Button btnCancelTodo;

    private int currentId;

    TodosRecyclerViewAdapter adapter;

    // It is not possible to pass an object to an Activity, so this has to be static.
    public static TodoHelper todoHelper;

    /**
     *
     * constructEditIntent - adding extended data to the intent
     *
     * @param todo
     * @param context
     * @return
     */

    public static Intent contstructEditIntent(TodoModel todo, Context context) {
        Intent intent = new Intent(context, AddOrUpdateTodoActivity.class);
        intent.putExtra(EXTRA_ID, todo.getId());
        intent.putExtra(EXTRA_TITLE, todo.getTitle());
        intent.putExtra(EXTRA_CONTENT, todo.getContent());
        return intent;
    }

    public static Intent constructCreateIntent(Context context) {
        return new Intent(context, AddOrUpdateTodoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_todo);
        initView();
        bindData();

    }

    // * save Method - adding new or updated data and closing out to create a stack on the view
    private void save() {
        persist();
        finish();
    }

    /**
     * The designed persist method is checking for two things.
     * It is checking if the currentId exists then the user has decided to click into this todolist item to edit context.
     * This will require setting the new field values with the new editText information associated with the currentId.
     *
     * The second part of this conditional is checking if there is no currentId then the user wants to create a new todolist item entirely.
     *
     * This also persists the updated data to disk
     */
    private void persist() {
        if (isUpdatingTodo(currentId)) {
            updateTodo(currentId);
        } else {
            createTodo();
        }
    }

    // * createTodo Method - adds new user inputted data
    private void createTodo() {
        int newId = todoHelper.getCurrentSize();
        TodoModel newTodo = new TodoModel(newId, etTitle.getText().toString(), etContent.getText().toString());
        todoHelper.insert(newTodo);
    }

    /**
     *
     * if the user quits the app, then when they come back to it, their data is still available.
     * Also displays the current data that exists for the provided fields. The data persists for the user
     *
     * @param currentId
     */

    private void updateTodo(int currentId) {
        TodoModel newTodo = new TodoModel(currentId, etTitle.getText().toString(), etContent.getText().toString());
        todoHelper.update(newTodo);
    }

    // * bindData Method - Allow change to meld to current data displayed
    private void bindData() {
        Intent paramIntent = getIntent();
        currentId = paramIntent.getIntExtra(EXTRA_ID, DEFAULT_ID);
        String title = paramIntent.getStringExtra(EXTRA_TITLE);
        String content = paramIntent.getStringExtra(EXTRA_CONTENT);


        if (isUpdatingTodo(currentId)) {
            etTitle.setText(title);
            etContent.setText(content);
        }
    }

    // * initView Method - connects variable to its resource and connects functionality with the save method and cancel functionality to prevent view stack
    private void initView() {
        etTitle = findViewById(R.id.et_todo_title);
        etContent = findViewById(R.id.et_todo_content);
        btnSaveTodo = findViewById(R.id.btn_save);
        btnCancelTodo = findViewById(R.id.btn_cancel);
        btnSaveTodo.setOnClickListener(v -> save());
        btnCancelTodo.setOnClickListener(v -> finish());
    }


    private boolean isUpdatingTodo(int currentId) {
        return currentId != DEFAULT_ID;
    }

    @Override
    public void onItemLongClick(int position) {
        todoHelper.list.remove(position);
        adapter.notifyItemRemoved(position);
    }
}