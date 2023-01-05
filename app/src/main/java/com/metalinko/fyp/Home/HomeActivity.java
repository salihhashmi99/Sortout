package com.metalinko.fyp.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metalinko.fyp.Accounts.AdminStudentComplainLogin;
import com.metalinko.fyp.Accounts.LoginActivity;
import com.metalinko.fyp.Accounts.SecurityLogin;
import com.metalinko.fyp.Admin.AdminHomeActivity;
import com.metalinko.fyp.Admin.AdminStudentComplaintActivity;
import com.metalinko.fyp.SuggestionForm;
import com.metalinko.fyp.databinding.ActivityHomeBinding;
import com.metalinko.fyp.slider_one;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                startActivity(intent);
            }
        });


        binding.btnsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , SecurityLogin.class);
                startActivity(intent);

            }
        });


        binding.btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , AdminHomeActivity.class);
                startActivity(intent);


            }
        });




    }

    public void info(View view) {

        Intent intent = new Intent(getApplicationContext() , slider_one.class);
        startActivity(intent);

    }
}