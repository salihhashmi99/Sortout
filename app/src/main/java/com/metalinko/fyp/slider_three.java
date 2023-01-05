package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metalinko.fyp.Home.HomeActivity;

public class slider_three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_three);
        overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void next(View view) {
        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(intent);
        finish();
    }
}