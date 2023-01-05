package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Accounts.AdminBusLogin;
import com.metalinko.fyp.databinding.ActivityAdminAplplyForTranscriptLoginBinding;

public class AdminAplplyForTranscriptLoginActivity extends AppCompatActivity {

    ActivityAdminAplplyForTranscriptLoginBinding binding;
    private String user_id, password;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            binding = ActivityAdminAplplyForTranscriptLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("adminapplyfortrans");


        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                user_id = binding.stdLoginid.getText().toString();
                password = binding.stdpassword.getText().toString();


                if (user_id.isEmpty()) {

                    binding.stdLoginid.setError("Please Enter User id");
                } else if (password.isEmpty()) {

                    binding.stdpassword.setError("Please Enter User id");

                } else {


        /*

                    AdminComplainTest securityLoginModel = new AdminComplainTest(
                            user_id,
                            password

                    );


                    mDatabase.push().setValue(securityLoginModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {


                                Toast.makeText(AdminBusLogin.this, "Done", Toast.LENGTH_SHORT).show();






                            }
                            else
                            {
                                Toast.makeText(AdminBusLogin.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(AdminBusLogin.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });





         */



                    mDatabase.orderByChild("user_id").equalTo(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if (snapshot.exists()) {


                                Toast.makeText(AdminAplplyForTranscriptLoginActivity.this, "Login", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), AdminStudentTranscriptActivity.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(AdminAplplyForTranscriptLoginActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                            Toast.makeText(AdminAplplyForTranscriptLoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }
        });


    }

    }
