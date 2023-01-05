package com.metalinko.fyp.StudentAllForms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.databinding.ActivityStudentComplaintBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentComplaintActivity extends AppCompatActivity {
        ActivityStudentComplaintBinding binding;
        private  String user_id , st_name, st_email,st_contact,st_system_id,st_title,st_dec;
    private DatabaseReference mDatabase;
    private StudentFormModel studentFormModel;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentComplaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("studentform");



       
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                st_name = binding.sdName.getText().toString();
                st_system_id =  binding.edtstdname.getText().toString();
                st_email =  binding.sdEmail.getText().toString();
                st_contact =  binding.edContact.getText().toString();
                st_title =  binding.secTitle.getText().toString();
                st_dec =  binding.sdDec.getText().toString();


                if(st_name.isEmpty())
                {
                    binding.sdName.setError("Enter Name");
                }
                else if(st_system_id.isEmpty())
                {

                    binding.edtstdname.setError("Enter ID");

                }

                else if(st_email.isEmpty())

                {
                    binding.sdEmail.setError("Enter Email");

                }

                else if(st_contact.isEmpty())
                {
                    binding.edContact.setError("Enter Contact");
                }

                else if(st_title.isEmpty())
                {
                    binding.secTitle.setError("Enter Title");
                }

                else if(st_dec.isEmpty())
                {
                    binding.sdDec.setError("Enter Description");
                }

                else
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String uid = user.getUid();



                      StudentFormModel studentFormModel = new StudentFormModel(
                            uid,
                            st_name,
                            st_system_id
                              ,
                              st_email,
                            st_contact,
                            st_title,
                            st_dec);
                    mDatabase.child(st_system_id).setValue(studentFormModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {




                            if(task.isSuccessful())
                            {
                         //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                           //    startActivity(intent);


                                binding.sdName.setText("");
                                binding.edtstdname.setText("");
                                binding.sdEmail.setText("");
                                binding.edContact.setText("");
                                binding.secTitle.setText("");
                                binding.sdDec.setText("");
                            }
                            else
                            {
                                Toast.makeText(StudentComplaintActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(StudentComplaintActivity.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }


        });
    }




}