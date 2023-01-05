package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metalinko.fyp.Home.HomeActivity;

public class slider_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_two);
        overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void next(View view) {
        Intent intent = new Intent(getApplicationContext() , slider_three.class);
        startActivity(intent);
        finish();    }

    public void skip(View view) {
        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(intent);
        finish();
    }
}