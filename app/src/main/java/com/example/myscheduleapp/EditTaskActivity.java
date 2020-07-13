package com.example.myscheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    EditText title, des, date;

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

    }

    public void onClickEdit (View view){
//        Intent intent = new Intent( this, AddTaskActivity.class);
//        startActivity(intent);

        this.finish();
    }


}
