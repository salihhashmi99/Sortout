package com.metalinko.fyp.Accounts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.metalinko.fyp.StudentHome.StudentHomeActivity;
import com.metalinko.fyp.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {
        ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    private String email , password, recheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                email =  binding.stdLoginEmail.getText().toString().trim();
                password = binding.stdpassword.getText().toString().trim();
                recheck = binding.stdpasswordcheck.getText().toString().trim();


                if(email.isEmpty())
                {
                    binding.stdLoginEmail.setError("Please Enter Email");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.stdLoginEmail.setError("Please enter a valid email address");
                }

                else if(password.isEmpty())
                {
                    binding.stdpassword.setError("Enter Password");
                }
                else if (password.length()<6)
                {
                    binding.stdpassword.setError("Password must be at least 5 characters");
                }
                else if(recheck.isEmpty())
                {
                    binding.stdpasswordcheck.setError("Re-Enter Password");
                }

                else if (!recheck.equals(password)) {
                    binding.stdpasswordcheck.setError("Password Would Not be matched");
                }
                else
                {

                    mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful())
                            {

                                Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                                startActivity(intent);
                                    finish();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Signup.this, ""+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }



            }
        });

        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                startActivity(intent);



            }
        });

    }


}

