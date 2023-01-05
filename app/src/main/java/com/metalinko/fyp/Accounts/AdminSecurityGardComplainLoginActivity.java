package com.metalinko.fyp.Accounts;

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
import com.metalinko.fyp.Admin.AdminSecurityComplaintActivity;
import com.metalinko.fyp.Admin.AdminStudentComplaintActivity;
import com.metalinko.fyp.databinding.ActivityAdminSecurityGardComplainLoginBinding;

public class AdminSecurityGardComplainLoginActivity extends AppCompatActivity {
    ActivityAdminSecurityGardComplainLoginBinding binding;
    private String user_id , password;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSecurityGardComplainLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        mDatabase = FirebaseDatabase.getInstance().getReference().child("adminsecuritycomplain");

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


                                Toast.makeText(AdminStudentComplainLogin.this, "Done", Toast.LENGTH_SHORT).show();






                            }
                            else
                            {
                                Toast.makeText(AdminStudentComplainLogin.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(AdminStudentComplainLogin.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });




                     */



                    mDatabase.orderByChild("user_id").equalTo(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if(snapshot.exists())
                            {


                                Toast.makeText(AdminSecurityGardComplainLoginActivity.this, "Login", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext() , AdminSecurityComplaintActivity.class);
                                startActivity(intent);


                            }else
                            {
                                Toast.makeText(AdminSecurityGardComplainLoginActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                            Toast.makeText(AdminSecurityGardComplainLoginActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                        }
                    });







                }




            }
        });



    }
}