package com.metalinko.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.Model.SuggestionFormModel;
import com.metalinko.fyp.StudentAllForms.StudentComplaintActivity;
import com.metalinko.fyp.databinding.ActivitySuggestionFormBinding;

public class SuggestionForm extends AppCompatActivity {

    ActivitySuggestionFormBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private  String  st_name, st_email,st_contact,st_title,st_dec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySuggestionFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("suggestionform");



        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                st_name = binding.sdName.getText().toString();
                st_email =  binding.sdEmail.getText().toString();
                st_contact =  binding.edContact.getText().toString();
                st_title =  binding.secTitle.getText().toString();
                st_dec =  binding.sdDec.getText().toString();


                if(st_name.isEmpty())
                {
                    binding.sdName.setError("Enter Name");
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




                    SuggestionFormModel studentFormModel = new SuggestionFormModel(

                            st_name,
                            st_email,
                            st_contact,
                            st_title,
                            st_dec);
                    mDatabase.child(st_email).setValue(studentFormModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {
                                //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                                //    startActivity(intent);


                                binding.sdName.setText("");
                                binding.sdEmail.setText("");
                                binding.edContact.setText("");
                                binding.secTitle.setText("");
                                binding.sdDec.setText("");
                            }
                            else
                            {
                                Toast.makeText(SuggestionForm.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(SuggestionForm.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }


        });


    }
}