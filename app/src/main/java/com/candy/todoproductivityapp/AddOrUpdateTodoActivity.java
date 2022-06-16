package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddOrUpdateTodoActivity extends AppCompatActivity {

    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_todo);

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(view -> finish());
    }
}