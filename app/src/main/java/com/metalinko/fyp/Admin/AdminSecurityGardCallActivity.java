package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metalinko.fyp.Model.SecurityGardCallModel;
import com.metalinko.fyp.StudentAllForms.StudentApplyForBusActivity;
import com.metalinko.fyp.databinding.ActivityAdminSecurityGardCallBinding;

public class AdminSecurityGardCallActivity extends AppCompatActivity {
    ActivityAdminSecurityGardCallBinding binding;
    private String sd_name , phone_number;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminSecurityGardCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("securitycall");

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sd_name = binding.stdLoginid.getText().toString();
                phone_number =  binding.stdphonenumber.getText().toString();
                if(sd_name.isEmpty()){


                    binding.btnSignIn.setError("Enter Name");
                }

                else if(phone_number.isEmpty())
                {
                    binding.stdphonenumber.setError("Enter Phone Number");
                }
                else
                {
                    SecurityGardCallModel securityGardCallModel = new SecurityGardCallModel(

                            sd_name , phone_number
                    );
                    mDatabase.child(phone_number).setValue(securityGardCallModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {

                                Toast.makeText(AdminSecurityGardCallActivity.this, "Save", Toast.LENGTH_SHORT).show();
                                //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                                //    startActivity(intent);


                                binding.stdLoginid.setText("");
                                binding.stdphonenumber.setText("");

                            }
                            else
                            {
                                Toast.makeText(AdminSecurityGardCallActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(AdminSecurityGardCallActivity.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}