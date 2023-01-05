package com.metalinko.fyp.Accounts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Model.SecurityLoginModel;
import com.metalinko.fyp.SecurityGuarfHome.SecurityGardHomeActivity;
import com.metalinko.fyp.StudentAllForms.StudentComplaintActivity;
import com.metalinko.fyp.databinding.ActivitySecurityLoginBinding;

public class SecurityLogin extends AppCompatActivity {
    ActivitySecurityLoginBinding binding;
    private String user_id , password;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

// Data
        mDatabase = FirebaseDatabase.getInstance().getReference().child("securitygardlogin");



        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {













                user_id =   binding.stdLoginid.getText().toString();
                password  = binding.stdpassword.getText().toString();





                if(user_id.isEmpty())
                {

                    binding.stdLoginid.setError("Please Enter User id");
                }

                else if(password.isEmpty())
                {

                    binding.stdpassword.setError("Please Enter User id");

                }

                else
                {



                    mDatabase.orderByChild("user_id").equalTo(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if(snapshot.exists())
                            {


                                Toast.makeText(SecurityLogin.this, "Login", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext() , SecurityGardHomeActivity.class);
                                startActivity(intent);


                            }else
                            {
                                Toast.makeText(SecurityLogin.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                            Toast.makeText(SecurityLogin.this, ""+error, Toast.LENGTH_SHORT).show();
                        }
                    });





                }






            }
        });



      binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent = new Intent(getApplicationContext() , SecuritySignup.class);
              startActivity(intent);
          }
      });



    }
}