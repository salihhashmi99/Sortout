package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Adopter.AdminSecurityComplainAdopter;
import com.metalinko.fyp.Adopter.AdminStudentApplyForBusAdopter;
import com.metalinko.fyp.Model.SecurityGardComplainModel;
import com.metalinko.fyp.Model.StudentBusModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.databinding.ActivityAdminSecurityComplaintBinding;

import java.util.ArrayList;

public class AdminSecurityComplaintActivity extends AppCompatActivity {
    ActivityAdminSecurityComplaintBinding binding;

    public ArrayList<SecurityGardComplainModel> arrayList;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private AdminSecurityComplainAdopter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminSecurityComplaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Data
        //    mDatabase = FirebaseDatabase.getInstance().getReference().child("securitygardform").child("securitycomplaint");

        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth


        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new AdminSecurityComplainAdopter(arrayList, this);
        recyclerView.setAdapter(adapter);


        show_data();


    }


    private void show_data() {


        Query query = mDatabase.child("securitygardform");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Data Reterive
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    SecurityGardComplainModel studentFormModel = new SecurityGardComplainModel();

                    studentFormModel.setSt_system_id(dataSnapshot.child("st_system_id").getValue().toString());
                    //  studentFormModel.setSt_name(dataSnapshot.child("st_dec").getValue().toString());


                    // For Intent

                    arrayList.add(studentFormModel);

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(AdminSecurityComplaintActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}