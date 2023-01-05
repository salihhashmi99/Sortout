package com.metalinko.fyp.SecurityGardForm;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metalinko.fyp.Model.SecurityGardComplainModel;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.StudentAllForms.StudentComplaintActivity;
import com.metalinko.fyp.databinding.ActivitySecurityGardComplainFormBinding;

public class SecurityGardComplainForm extends AppCompatActivity {
    ActivitySecurityGardComplainFormBinding binding;
    private  String  st_name, st_system_id , st_email,st_contact,st_title,st_dec;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityGardComplainFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("securitygardcomplain");



                binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        st_name = binding.edname.getText().toString();
                        st_system_id = binding.edsysid.getText().toString();
                        st_email =  binding.edemail.getText().toString();
                        st_contact = binding.edcontact.getText().toString();
                        st_title = binding.secTitle.getText().toString();
                        st_dec = binding.stdDescArea.getText().toString();




                        if(st_name.isEmpty())
                        {

                            binding.edname.setError("Enter Name");
                        }

                        else if(st_system_id.isEmpty())
                        {
                            binding.edsysid.setError("Enter System id");
                        }

                        else if(st_email.isEmpty())
                        {

                            binding.edemail.setError("Enter Email");
                        }

                        else if(st_contact.isEmpty())
                        {
                            binding.edcontact.setError("Enter Contact");
                        }

                        else if(st_title.isEmpty())
                        {

                            binding.secTitle.setError("Enter Title");
                        }
                        else if(st_dec.isEmpty())
                        {

                            binding.stdDescArea.setError("Enter Description");
                        }

                        else

                        {



                            SecurityGardComplainModel securityGardComplainModel = new SecurityGardComplainModel(


                                    st_name, st_system_id , st_email,st_contact,st_title,st_dec

                            );





                            mDatabase.child(st_system_id).setValue(securityGardComplainModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    if(task.isSuccessful())
                                    {


                                        Toast.makeText(SecurityGardComplainForm.this, "Completed", Toast.LENGTH_SHORT).show();
                                        //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                                        //    startActivity(intent);


                                        binding.edname.setText("");
                                        binding.edsysid.setText("");
                                        binding.edemail.setText("");
                                        binding.edcontact.setText("");
                                        binding.secTitle.setText("");
                                        binding.stdDescArea.setText("");
                                    }
                                    else
                                    {
                                        Toast.makeText(SecurityGardComplainForm.this, "Error", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {


                                    Toast.makeText(SecurityGardComplainForm.this, ": "+e, Toast.LENGTH_SHORT).show();
                                }
                            });



                        }








                    }
                });

    }
}