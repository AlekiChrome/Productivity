package view.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candy.todoproductivityapp.R;

import uber.candy.todo.model.TodoModel;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public TextView tvContent;

    public TodoViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_todo_title);
        this.tvContent = itemView.findViewById(R.id.tv_content);
    }

    public void bind(TodoModel todo) {
        tvTitle.setText(todo.getTitle());
        tvContent.setText(todo.getContent());
    }
}
