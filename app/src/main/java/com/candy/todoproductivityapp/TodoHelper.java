package com.candy.todoproductivityapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

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
    public List<TodoModel> latestList;



    /**
     *
     * @param applicationContext
     */
    public TodoHelper(Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences(NAME_OF_SHARED_PREFS, MODE_PRIVATE);
        latestList = new ArrayList<>();
    }


    // * get content stored on disk (tell Gson to convert list of todos)
    public void getFromDisk() {
        List<TodoModel> latestList = fetchFromDisk();
        this.latestList = latestList;

        // The list will be null when there is not existing data (or it's mangled). So start with an
        // empty list in that case.
        if (this.latestList == null) {
            this.latestList = new ArrayList<>();
        }
    }

    public List<TodoModel> fetchFromDisk() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, null);
        Type type = new TypeToken<List<TodoModel>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * update Method - gets content from disk. If content id already exists update the changes, otherwise create new list item.
     * @param newTodo
     */
    public void update(TodoModel newTodo) {
//        List<TodoModel> currentList = getFromDisk();
        List<TodoModel> newList = new ArrayList<>();

        for (TodoModel current : latestList) {
            if (current.getId() == newTodo.getId()) {
                newList.add(newTodo);
            } else {
                newList.add(current);
            }
        }
        latestList = newList;
        // 1.-  adapter.setNewList(list);

        // list.clear()
        // list.readFromSharedPreferences();
//        adapter.notifyDataSetChanged();
        saveInternally();
    }

    /**
     * insert Method - adds content to list if it exists or has been store on disk or creates a new list if no previous content was created. Also adds to json.
     * @param newTodo
     */
    public void insert(TodoModel newTodo) {
//        List<TodoModel> currentList = getFromDisk();
//        List<TodoModel> newList = new ArrayList<>(currentList);
        latestList.add(newTodo);

        adapter.notifyDataSetChanged();

        saveInternally();
    }


    private void saveInternally() {
        sharedPreferences.edit().putString(KEY_LIST, new Gson().toJson(latestList)).apply();
    }

    // * getCurrentSize Method - gets the length of the added or removes items in the list
    public int getCurrentSize() {
//        List<TodoModel> todoModels = getFromDisk();
        return latestList.size() + 1;
    }
}

