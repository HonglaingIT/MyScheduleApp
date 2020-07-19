package com.example.myscheduleapp;

import android.widget.EditText;

public class Todo {

     String todoID;
   String title;
 String description;
 String deadline;

    public Todo(){
    }


    public Todo (String id, String title, String description, String date){
        this.todoID = id;
        this.title = title;
        this.description = description;
        this.deadline = date;
    }

    public String getTodoID() {
        return todoID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }
}