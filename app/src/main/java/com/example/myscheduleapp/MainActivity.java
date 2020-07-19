package com.example.myscheduleapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TodosAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    DatabaseReference databaseTodos;
    List<Todo> todoList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(null);

        firebaseAuth= FirebaseAuth.getInstance();
        databaseTodos = FirebaseDatabase.getInstance().getReference("todos");
        currentUser = firebaseAuth.getCurrentUser();

        todoList = new ArrayList<>();
        if(currentUser != null){
            setContentView(R.layout.activity_main);
            progressBar = findViewById(R.id.progress_bar);
            recyclerView = findViewById(R.id.recyclerView);



            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);




//        RecyclerView recyclerView = findViewById(R.id.recycler_view);

            // Create and set a layout manager
            recyclerView.setLayoutManager( new LinearLayoutManager(this));
            //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
           // recyclerView.setLayoutManager(layoutManager);

            // Create and set an adapter
            //
//        TodosAdapter adapter = new TodosAdapter(todos);
//        recyclerView.setAdapter(adapter);

            adapter = new TodosAdapter(this, todoList);
            recyclerView.setAdapter(adapter);
//            this.finish();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
        }

            else {
            this.finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
//        setContentView(R.layout.activity_main);
//
//
//        progressBar = findViewById(R.id.progress_bar);
//        recyclerView = findViewById(R.id.recycler_view);
//
//
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//
//
////        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//
//        // Create and set a layout manager
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // Create and set an adapter
//        //
////        TodosAdapter adapter = new TodosAdapter(todos);
////        recyclerView.setAdapter(adapter);
//        loadTodos();
    }




    // To create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_option, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addtask){
            Intent intent = new Intent( this, AddTaskActivity.class);
            startActivity(intent);
        } if (item.getItemId() == R.id.logout){
            logout();
        }

        return true;
    }

    public void logout (){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logged out!", Toast.LENGTH_LONG).show();

        //String email = currentUser.getEmail().toString();
        this.finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onClickButton (View view){
        Intent intent = new Intent( this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void onClickEdit( View view){
        Intent intent = new Intent( this, EditTaskActivity.class);
        TextView title = view.findViewById(R.id.txt_title);
        TextView des = view.findViewById(R.id.txt_des);
        TextView date = view.findViewById(R.id.txt_deadline);
        TextView id = view.findViewById(R.id.txt_id);
        intent.putExtra("task", title.getText().toString());
        intent.putExtra("des", des.getText().toString());
        intent.putExtra("date", date.getText().toString());
        intent.putExtra("id", id.getText().toString());
        startActivity(intent);
    }

//    private void loadTodos() {
//
//        // Show loading
//        showLoading(true);
//
//        String url = "https://my.api.mockaroo.com/testinggggggg.json?key=5df54bd0";
//
//        // Create a request
//        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // Convert json string to array of Email using Gson
//                Gson gson = new Gson();
//                Todo[] todos = gson.fromJson(response, Todo[].class);
//                // Create and set an adapter
//                TodosAdapter adapter = new TodosAdapter(todos);
//                recyclerView.setAdapter(adapter);
//
//                // Hide the progress bar and show recycler view
//                showLoading(false);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Something error while loading data from the server", Toast.LENGTH_LONG).show();
//                Log.d("piuecom", "Load data error: " + error.getMessage());
//                // Hide the progress bar and show recycler view
//                showLoading(false);
//            }
//        });
//
//        // Add the request to the Queue
//        Volley.newRequestQueue(this).add(request);
//
//    }

    private void loadTodos() {


        // Show loading

    }

    @Override
    protected void onStart() {
        super.onStart();

        showLoading(true);

        databaseTodos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                todoList.clear();

                if (dataSnapshot.exists()) {
                for (DataSnapshot todoSnapshot : dataSnapshot.getChildren()) {

                    Todo todo = todoSnapshot.getValue(Todo.class);
                    todoList.add(todo);
                    showLoading(false);
                }
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something error while loading data from the server", Toast.LENGTH_LONG).show();


            }

        });
    }

    private void showLoading(boolean state){
        if(state){
            //recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
           // recyclerView.setVisibility(View.VISIBLE);
        }
    }


}
