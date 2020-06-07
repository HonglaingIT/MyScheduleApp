package com.example.myscheduleapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private TextView txtDescription;
    private TextView txtDeadline;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txt_title);
        txtDescription = itemView.findViewById(R.id.txt_des);
        txtDeadline = itemView.findViewById(R.id.txt_deadline);
    }

    public void bind(Todo todo){
        txtTitle.setText(todo.getTitle());
        txtDescription.setText(todo.getDescription());
        txtDeadline.setText(todo.getDeadline());
    }

}
