package com.example.myscheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTaskActivity extends AppCompatActivity {

    EditText title, des, date;
    String id, titleTxt, desTxt, dateTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        title = findViewById(R.id.editText4);
        des = findViewById(R.id.editText5);
        date = findViewById(R.id.editText6);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("task"));
        des.setText(intent.getStringExtra("des"));
        date.setText(intent.getStringExtra("date"));
        id = intent.getStringExtra("id");

    }

    public void onClickEdit (View view){

        titleTxt = title.getText().toString().trim();
        desTxt = des.getText().toString().trim();
        dateTxt = date.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("todos").child(id);
        Todo todo = new Todo(id,titleTxt,desTxt,dateTxt);
        databaseReference.setValue(todo);
        Toast.makeText(this, "Updated successfully!",Toast.LENGTH_LONG).show();
        this.finish();
    }
 public void onClickDelete (View view){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("todos").child(id);

        databaseReference.removeValue();
        Toast.makeText(this, "Deleted!",Toast.LENGTH_LONG).show();
        this.finish();
    }




}
