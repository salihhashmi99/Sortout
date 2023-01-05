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
import com.metalinko.fyp.databinding.ActivityAdminShowBusBinding;

public class AdminShowBusActivity extends AppCompatActivity {
        ActivityAdminShowBusBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminShowBusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference().child("studentbus");
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        String id = getIntent().getStringExtra("key");




        mDatabase.orderByChild("system_id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {




                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {



                    binding.edtname.setText(dataSnapshot.child("full_Name").getValue().toString());
                    binding.edtstid.setText(dataSnapshot.child("system_id").getValue().toString());
                    binding.stdcource.setText(dataSnapshot.child("course").getValue().toString());
                    binding.stdsemester.setText(dataSnapshot.child("samester").getValue().toString());
                    binding.stdBuschallan.setText(dataSnapshot.child("challan_Form").getValue().toString());
                    binding.stdbusno.setText(dataSnapshot.child("bus_No").getValue().toString());
                    binding.edtstopname.setText(dataSnapshot.child("shop_Number").getValue().toString());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(AdminShowBusActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });








    }
}