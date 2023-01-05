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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.metalinko.fyp.StudentHome.StudentHomeActivity;
import com.metalinko.fyp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
        ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private String email , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , Signup.class);
                startActivity(intent);
            }
        });


        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                email =  binding.stdLoginEmail.getText().toString();
                password = binding.stdpassword.getText().toString();


                if(email.isEmpty())
                {
                    binding.stdLoginEmail.setError("Please Enter Email");
                }

                else if(password.isEmpty())
                {
                    binding.stdpassword.setError("Enter Password");
                }

                else
                {




                mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful())
                        {

                            Intent intent = new Intent(getApplicationContext() , StudentHomeActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Toast.makeText(LoginActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });

                }


            }
        });


        binding.secForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }



}