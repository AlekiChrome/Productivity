package com.candy.todoproductivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserCredentialsActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_NAME = "userPrefs";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";

    EditText etUserName;
    EditText etUserEmail;
    Button btnConfirm;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_credentials);

        etUserName = findViewById(R.id.et_user_name);
        etUserEmail = findViewById(R.id.et_user_email);

        btnConfirm = findViewById(R.id.btn_confirm);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        String user = sharedPreferences.getString(KEY_NAME, null);

        if(user != null) {
            Intent intent = new Intent(UserCredentialsActivity.this, TodosRecyclerViewActivity.class);
            startActivity(intent);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, etUserName.getText().toString());
                editor.putString(KEY_EMAIL, etUserEmail.getText().toString());
                editor.apply();

                Intent intent = new Intent(UserCredentialsActivity.this, TodosRecyclerViewActivity.class);
                startActivity(intent);

                Toast.makeText(UserCredentialsActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}