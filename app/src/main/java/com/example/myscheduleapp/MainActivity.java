package com.example.myscheduleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

//        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Create and set a layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Create and set an adapter
//        Todo[] todos = loadTodos();
//        TodosAdapter adapter = new TodosAdapter(todos);
//        recyclerView.setAdapter(adapter);
        loadTodos();
    }

    public void onClickButton (View view){


        Intent intent = new Intent( this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void onClickEdit (View view){


        Intent intent = new Intent( this, EditTaskActivity.class);
        startActivity(intent);
    }

    private void loadTodos() {

        // Show loading
        showLoading(true);

        // Load todo from the server using Volley library
        String url = "http://192.168.123.14:8888/todos.php";

        // Create a request
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Convert json string to array ofTodo using Gson
                Gson gson = new Gson();
                Todo[] todos = gson.fromJson(response, Todo[].class);
                // Create and set an adapter
                TodosAdapter adapter = new TodosAdapter(todos);
                recyclerView.setAdapter(adapter);

                // Hide the progress bar and show recycler view
                showLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something error while loading data from the server", Toast.LENGTH_LONG).show();
                Log.d("myscheduleapp", "Load data error: " + error.getMessage());
                // Hide the progress bar and show recycler view
                showLoading(false);
            }
        });

        // Add the request to the Queue
        Volley.newRequestQueue(this).add(request);

    }

    private void showLoading(boolean state){
        if(state){
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


}
