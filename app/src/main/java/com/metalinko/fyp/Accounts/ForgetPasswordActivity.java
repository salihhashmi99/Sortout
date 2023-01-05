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
import com.google.firebase.auth.FirebaseAuth;
import com.metalinko.fyp.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;

    private  String email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        mAuth = FirebaseAuth.getInstance();

            binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    email = binding.stdpasswrdid.getText().toString();

                    if(email.isEmpty())
                    {

                        binding.stdpasswrdid.setError("Enter Email");
                    }

                    else
                    {


                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {



                                if(task.isSuccessful())
                                {

                                    Toast.makeText(ForgetPasswordActivity.this,"Done sent",Toast.LENGTH_LONG).show();


                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                    finish();
                                }

                                else
                                {

                                    Toast.makeText(ForgetPasswordActivity.this,"Error Occurred",Toast.LENGTH_LONG).show();


                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Toast.makeText(ForgetPasswordActivity.this, ""+
                                        e, Toast.LENGTH_SHORT).show();

                            }
                        });



                    }


                }
            });






    }
}