package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class no_internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }

    public void retry(View view) {

        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(intent);
        finish();
    }
}