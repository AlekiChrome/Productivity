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

    public TodosRecyclerViewAdapter adapter;

    private final SharedPreferences sharedPreferences;
    public List<TodoModel> list;



    /**
     *
     * @param applicationContext
     */
    public TodoHelper(Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences(NAME_OF_SHARED_PREFS, MODE_PRIVATE);
        list = new ArrayList<>();
    }


    // * get content stored on disk (tell Gson to convert list of todos)
    public void getFromDisk() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);

        Type type = new TypeToken<List<TodoModel>>() {
        }.getType();

        list = gson.fromJson(json, type);

        // The list will be null when there is not existing data (or it's mangled). So start with an
        // empty list in that case.
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    /**
     * update Method - gets content from disk. If content id already exists update the changes, otherwise create new list item.
     * @param newTodo
     */
    public void update(TodoModel newTodo) {
//        List<TodoModel> currentList = getFromDisk();
//        List<TodoModel> newList = new ArrayList<>();

        for (TodoModel current : list) {
            if (current.getId() == newTodo.getId()) {
                list.add(current);
            } else {
                list.add(newTodo);
            }
        }
        saveInternally();
    }

    /**
     * insert Method - adds content to list if it exists or has been store on disk or creates a new list if no previous content was created. Also adds to json.
     * @param newTodo
     */
    public void insert(TodoModel newTodo) {
//        List<TodoModel> currentList = getFromDisk();
//        List<TodoModel> newList = new ArrayList<>(currentList);
        list.add(newTodo);

        adapter.notifyDataSetChanged();

        saveInternally();
    }


    private void saveInternally() {
        sharedPreferences.edit().putString(KEY_LIST, new Gson().toJson(list)).apply();
    }

    // * getCurrentSize Method - gets the length of the added or removes items in the list
    public int getCurrentSize() {
//        List<TodoModel> todoModels = getFromDisk();
        return list.size() + 1;
    }
}

