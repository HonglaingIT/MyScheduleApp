package com.example.myscheduleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodosAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    // Dataset
    private Todo[] todos;

    public TodosAdapter(Todo[] todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Load a layout file
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_holder_todo, parent, false);

        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos[position];
        holder.bind(todo);
    }

    @Override
    public int getItemCount() {
        return todos.length;
    }
}
