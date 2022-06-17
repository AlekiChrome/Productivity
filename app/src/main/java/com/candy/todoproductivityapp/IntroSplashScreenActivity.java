package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class IntroSplashScreenActivity extends AppCompatActivity {

    TextView tvLogo;
    TextView tvSubTitle;

    Button btnAccessTodos;

    Animation animateBtn;
    Animation animateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash_screen);

        btnAccessTodos = findViewById(R.id.btn_access_todos);
        tvLogo = findViewById(R.id.tv_name_logo);
        tvSubTitle = findViewById(R.id.tv_sub_title);

        animateBtn = AnimationUtils.loadAnimation(this, R.anim.animate_button);
        btnAccessTodos.setAnimation(animateBtn);

        animateText = AnimationUtils.loadAnimation(this, R.anim.animate_button);
        tvLogo.setAnimation(animateText);
        tvSubTitle.setAnimation(animateText);

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