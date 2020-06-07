package com.example.myscheduleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Create and set a layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Create and set an adapter
        Todo[] todos = loadTodos();
        TodosAdapter adapter = new TodosAdapter(todos);
        recyclerView.setAdapter(adapter);

    }

    public void onClickButton (View view){


        Intent intent = new Intent( this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void onClickEdit (View view){


        Intent intent = new Intent( this, EditTaskActivity.class);
        startActivity(intent);
    }

    private Todo[] loadTodos(){
        // Temporary data
        Todo todo1 = new Todo();
        todo1.setTitle("CS HW");
        todo1.setDescription("Android");
        todo1.setDeadline("11/6/2020");

        Todo todo2 = new Todo();
        todo2.setTitle("BUS HW");
        todo2.setDescription("Entrepreneurship");
        todo2.setDeadline("23/6/2020");

        Todo todo3 = new Todo();
        todo3.setTitle("MIS HCI HW");
        todo3.setDescription("HCI");
        todo3.setDeadline("12/6/2020");

        Todo todo4 = new Todo();
        todo4.setTitle("CS 360 HW");
        todo4.setDescription("Java");
        todo4.setDeadline("15/6/2020");

        return new Todo[]{todo1, todo2, todo3, todo4};
    }




}
