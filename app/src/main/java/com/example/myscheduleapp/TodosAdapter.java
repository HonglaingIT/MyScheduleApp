package com.example.myscheduleapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodoViewHolder> {

    // Dataset
    private Context mCtx;
    private List<Todo> todoList;

    public TodosAdapter(Context mCtx, List<Todo> todoList) {
        this.mCtx = mCtx;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.view_holder_todo, parent, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.textViewID.setText(todo.todoID);
        holder.textViewTitle.setText(todo.title);
        holder.textViewDes.setText(todo.description);
        holder.textViewDate.setText(todo.deadline);
    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewID, textViewTitle, textViewDes, textViewDate;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.txt_id);
            textViewTitle = itemView.findViewById(R.id.txt_title);
            textViewDes = itemView.findViewById(R.id.txt_des);
            textViewDate = itemView.findViewById(R.id.txt_deadline);
        }
    }
}