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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metalinko.fyp.Model.SecurityLoginModel;
import com.metalinko.fyp.databinding.ActivitySecuritySignupBinding;
import com.metalinko.fyp.databinding.ActivitySignupBinding;

public class SecuritySignup extends AppCompatActivity {
        ActivitySecuritySignupBinding binding;
    private String user_id , password,check;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecuritySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mDatabase = FirebaseDatabase.getInstance().getReference().child("securitygardlogin");


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                user_id = binding.stdsignupid.getText().toString();
                password = binding.stdpassword.getText().toString();
                check = binding.stdpasswordcheck.getText().toString();


                if (user_id.isEmpty()) {

                    binding.stdsignupid.setError("Please Enter User id");
                } else if (password.isEmpty()) {

                    binding.stdpassword.setError("Please Enter Password");

                }  else if (check.isEmpty()|| check.equals(password)) {

                    binding.stdpasswordcheck.setError("Password Doesnot match");

                } else {
                    SecurityLoginModel securityLoginModel = new SecurityLoginModel(user_id, password);



                    mDatabase.push().setValue(securityLoginModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {


                                Toast.makeText(SecuritySignup.this, "Done", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext() , SecurityLogin.class);
                                startActivity(intent);
                                finish();




                            }
                            else
                            {
                                Toast.makeText(SecuritySignup.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(SecuritySignup.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }













            }
        });
    }

    public void nex(View view) {

        Intent intent = new Intent(getApplicationContext() , SecurityLogin.class);
        startActivity(intent);
        finish();
    }
}