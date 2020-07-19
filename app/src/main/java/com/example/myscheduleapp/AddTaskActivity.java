package com.example.myscheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity {

    private DatabaseReference databaseTodos;
    EditText task, des , date1;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task = findViewById(R.id.editText);
        des = findViewById(R.id.editText2);
        date1 = findViewById(R.id.editText3);

        databaseTodos = FirebaseDatabase.getInstance().getReference("todos");

    }

    public void onClickAdd (View view){

        String title = task.getText().toString().trim();
        String description = des.getText().toString().trim();
        String date = date1.getText().toString().trim();
        if (title.isEmpty()){
            Toast.makeText(this, "Please fill in the title!", Toast.LENGTH_LONG).show();
        }
        else if (description.isEmpty()) {
            Toast.makeText(this, "Please fill in the description!", Toast.LENGTH_LONG).show();
        }
        else if (date.isEmpty()){
                Toast.makeText(this, "Please fill in the date!", Toast.LENGTH_LONG).show();
        }
        else {
            String id = databaseTodos.push().getKey();
            Todo todo = new Todo (id,title,description,date);
            databaseTodos.child(id).setValue(todo);
            Toast.makeText(this, "Task added!", Toast.LENGTH_LONG).show();

            finish();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        databaseReference.child(user.getUid())
        }
    }


}
