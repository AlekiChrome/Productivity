package com.candy.todoproductivityapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uber.candy.todo.model.TodoModel;
import view.holder.TodoViewHolder;

public class TodosRecyclerViewAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private List<TodoModel> currentList;
    private final Context context;
    private TodosRecyclerViewInterface todosRecyclerViewInterface;


    public TodosRecyclerViewAdapter(List<TodoModel> currentList, Context context) {
        this.currentList = currentList;
        this.context = context;
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View todoItem = inflater.inflate(R.layout.single_todo_item_layout, parent, false);
//        TodoViewHolder todoViewHolder = new TodoViewHolder(todoItem);
//
//        return todoViewHolder;

        return new TodoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_todo_item_layout, parent, false), context);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.bind(currentList.get(position));
    }


    @Override
    public int getItemCount() {
        return currentList.size();
    }

}
