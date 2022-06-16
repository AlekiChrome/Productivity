package view.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candy.todoproductivityapp.AddOrUpdateTodoActivity;
import com.candy.todoproductivityapp.R;

import uber.candy.todo.model.TodoModel;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvContent;
    private Context context;

    public TodoViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_display_todo_title);
        tvContent = itemView.findViewById(R.id.tv_display_todo_content);
        this.context = context;
    }

    public void bind(TodoModel todo) {
        tvTitle.setText(todo.getTitle());
        tvContent.setText(todo.getContent());

        itemView.setOnClickListener(v -> {
            showDetails(todo);
        });
    }

    private void showDetails(TodoModel todo) {
        context.startActivity(AddOrUpdateTodoActivity.contstructEditIntent(todo, context));
    }

}

