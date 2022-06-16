package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroSplashScreenActivity extends AppCompatActivity {

    Button btnAccessTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash_screen);

        btnAccessTodos = findViewById(R.id.btn_access_todos);
        btnAccessTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constructEditIntent();
            }

            private void constructEditIntent() {
                Intent intent = new Intent(IntroSplashScreenActivity.this, TodosRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}