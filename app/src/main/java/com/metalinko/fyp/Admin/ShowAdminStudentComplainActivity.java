package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Accounts.SecurityLogin;
import com.metalinko.fyp.SecurityGuarfHome.SecurityGardHomeActivity;
import com.metalinko.fyp.databinding.ActivityShowAdminStudentComplainBinding;

public class ShowAdminStudentComplainActivity extends AppCompatActivity {
        ActivityShowAdminStudentComplainBinding binding;


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       binding =  ActivityShowAdminStudentComplainBinding.inflate(getLayoutInflater()) ;
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        // Data
        mDatabase = FirebaseDatabase.getInstance().getReference().child("studentform");


        String id = getIntent().getStringExtra("key");









        mDatabase.orderByChild("st_system_id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {




                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {



                    binding.edtstdname.setText(dataSnapshot.child("st_system_id").getValue().toString());
                    binding.sdName.setText(dataSnapshot.child("st_name").getValue().toString());
                    binding.sdEmail.setText(dataSnapshot.child("st_email").getValue().toString());
                    binding.edContact.setText(dataSnapshot.child("st_contact").getValue().toString());
                    binding.secTitle.setText(dataSnapshot.child("st_title").getValue().toString());
                    binding.sdDec.setText(dataSnapshot.child("st_dec").getValue().toString());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(ShowAdminStudentComplainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });










    }
}