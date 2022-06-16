package com.candy.todoproductivityapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uber.candy.todo.model.TodoModel;

public class TodoHelper {

    public static final String NAME_OF_SHARED_PREFS = "UserContent";
    public static final String KEY_LIST = "User_Todo_Data";

    private final SharedPreferences sharedPreferences;

    public TodoHelper(Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences(NAME_OF_SHARED_PREFS, MODE_PRIVATE);
    }

    public List<TodoModel> getFromDisk() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);

        Type type = new TypeToken<List<TodoModel>>() {
        }.getType();

        List<TodoModel> todoModels = gson.fromJson(json, type);
        return todoModels == null ? new ArrayList<>() : new ArrayList<>(todoModels) ;
    }
}
