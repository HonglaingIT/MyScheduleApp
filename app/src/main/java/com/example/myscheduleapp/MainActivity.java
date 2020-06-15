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

    private void loadTodos(){
        // Temporary data
//        Todo todo1 = new Todo();
//        todo1.setTitle("CS HW");
//        todo1.setDescription("Android");
//        todo1.setDeadline("11/6/2020");
//
//        Todo todo2 = new Todo();
//        todo2.setTitle("BUS HW");
//        todo2.setDescription("Entrepreneurship");
//        todo2.setDeadline("23/6/2020");
//
//        Todo todo3 = new Todo();
//        todo3.setTitle("MIS HCI HW");
//        todo3.setDescription("HCI");
//        todo3.setDeadline("12/6/2020");
//
//        Todo todo4 = new Todo();
//        todo4.setTitle("CS 360 HW");
//        todo4.setDescription("Java");
//        todo4.setDeadline("15/6/2020");
//
//        return new Todo[]{todo1, todo2, todo3, todo4};
        // Show loading
        showLoading(true);

        // Load email from the server using Volley library
        String url = "http://10.0.2.2/example/mails.php";

        // Create a request
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Convert json string to array of Email using Gson
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
                Log.d("piuecom", "Load data error: " + error.getMessage());
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
