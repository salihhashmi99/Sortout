package com.metalinko.fyp.SecurityGuarfHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.metalinko.fyp.CheckForDutyActivity;
import com.metalinko.fyp.R;
import com.metalinko.fyp.SecurityGardForm.SecurityGardCallShowRecyclerActivity;
import com.metalinko.fyp.SecurityGardForm.SecurityGardComplainForm;
import com.metalinko.fyp.activity.Login;
import com.metalinko.fyp.databinding.ActivitySecurityGardHomeBinding;

public class SecurityGardHomeActivity extends AppCompatActivity {
        ActivitySecurityGardHomeBinding binding ;

        Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityGardHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());






        binding.seccomplaintsbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , SecurityGardComplainForm.class);
                startActivity(intent);
            }
        });



        binding.stdbuscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext() , SecurityGardCallShowRecyclerActivity.class);

                startActivity(intent);





            }
        });


        binding.stdtranscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext() , CheckForDutyActivity.class);
                startActivity(intent);


            }
        });


    }


    public void chat(View view) {

        Intent intent = new Intent(getApplicationContext() , Login.class);
        startActivity(intent);

    }
}