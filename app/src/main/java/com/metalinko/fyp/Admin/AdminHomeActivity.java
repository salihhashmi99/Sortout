package com.metalinko.fyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metalinko.fyp.Accounts.AdminBusLogin;
import com.metalinko.fyp.Accounts.AdminSecurityGardCallLoginActivity;
import com.metalinko.fyp.Accounts.AdminStudentComplainLogin;
import com.metalinko.fyp.Home.HomeActivity;
import com.metalinko.fyp.MainActivity;
import com.metalinko.fyp.databinding.ActivityAdminHomeBinding;

public class AdminHomeActivity extends AppCompatActivity {
        ActivityAdminHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.adminComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , AdminStudentComplainLogin.class);
                startActivity(intent);




            }
        });



        binding.adminstudentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , AdminBusLogin.class);

                startActivity(intent);



            }
        });


        binding.adminTranscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , AdminAplplyForTranscriptLoginActivity.class);
                startActivity(intent);



            }
        });


        binding.adminSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , AdminSecurityGardCallLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(intent);
    }
}