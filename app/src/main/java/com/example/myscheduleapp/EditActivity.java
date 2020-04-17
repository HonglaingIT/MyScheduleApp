package com.example.myscheduleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

public class EditActivity extends AppCompatActivity {

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//  setContentView(R.layout.activity_add_task);
//    }

    public void onClickAdd (View view){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}