package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.databinding.ActivityAdminShowSecurityComplainBinding;

public class AdminShowSecurityComplainActivity extends AppCompatActivity {
    ActivityAdminShowSecurityComplainBinding binding;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminShowSecurityComplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Data
        mDatabase = FirebaseDatabase.getInstance().getReference().child("securitygardform");


        String id = getIntent().getStringExtra("key");









        mDatabase.orderByChild("st_system_id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {




                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {



                    binding.edname.setText(dataSnapshot.child("st_name").getValue().toString());
                    binding.edsysid.setText(dataSnapshot.child("st_system_id").getValue().toString());
                    binding.edemail.setText(dataSnapshot.child("st_email").getValue().toString());
                    binding.edcontact.setText(dataSnapshot.child("st_contact").getValue().toString());
                    binding.secTitle.setText(dataSnapshot.child("st_title").getValue().toString());
                    binding.stdDescArea.setText(dataSnapshot.child("st_dec").getValue().toString());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(AdminShowSecurityComplainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });







    }
}