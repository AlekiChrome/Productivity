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


    /**
     *
     * @param applicationContext
     */
    public TodoHelper(Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences(NAME_OF_SHARED_PREFS, MODE_PRIVATE);
    }

    // * get content stored on disk (tell Gson to convert list of todos)
    public List<TodoModel> getFromDisk() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);

        Type type = new TypeToken<List<TodoModel>>() {
        }.getType();

        List<TodoModel> todoModels = gson.fromJson(json, type);
        return todoModels == null ? new ArrayList<>() : new ArrayList<>(todoModels) ;
    }

    /**
     * update Method - gets content from disk. If content id already exists update the changes, otherwise create new list item.
     * @param newTodo
     */
    public void update(TodoModel newTodo) {
        List<TodoModel> currentList = getFromDisk();
        List<TodoModel> newList = new ArrayList<>();

        for (TodoModel current : currentList) {
            if (current.getId() == newTodo.getId()) {
                newList.add(current);
            } else {
                newList.add(newTodo);
            }
        }
        saveInternally(newList);
    }

    /**
     * insert Method - adds content to list if it exists or has been store on disk or creates a new list if no previous content was created. Also adds to json.
     * @param newTodo
     */
    public void insert(TodoModel newTodo) {
        List<TodoModel> currentList = getFromDisk();
        List<TodoModel> newList = new ArrayList<>(currentList);
        newList.add(newTodo);

        saveInternally(newList);
    }

    /**
     * saveInternally Method - saves data in json format to be accessed internally
     * @param newList
     */
    private void saveInternally(List<TodoModel> newList) {
        sharedPreferences.edit().putString(KEY_LIST, new Gson().toJson(newList)).apply();
    }

    // * getCurrentSize Method - gets the length of the added or removes items in the list
    public int getCurrentSize() {
        List<TodoModel> todoModels = getFromDisk();
        return todoModels.size() + 1;
    }
}

