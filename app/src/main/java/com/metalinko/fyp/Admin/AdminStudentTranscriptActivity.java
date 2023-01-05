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
import com.metalinko.fyp.Adopter.AdminStudentAdopter;
import com.metalinko.fyp.Adopter.AdminStudentTransAdopter;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.Model.StudnetApplyForTranscriptModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.databinding.ActivityAdminStudentTranscriptBinding;
import com.metalinko.fyp.databinding.ActivityShowAdminStudentTransportMainBinding;
import com.metalinko.fyp.databinding.ActivityStudentApplyForTranscriptBinding;

import java.util.ArrayList;

public class AdminStudentTranscriptActivity extends AppCompatActivity {
    ActivityAdminStudentTranscriptBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private AdminStudentTransAdopter adapter;
    public ArrayList<StudnetApplyForTranscriptModel> arrayList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminStudentTranscriptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Data
    //    mDatabase = FirebaseDatabase.getInstance().getReference().child("studentapplyfortranform").child("studenttranscription");


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new AdminStudentTransAdopter(arrayList, this);
        recyclerView.setAdapter(adapter);

        show_data();




    }


    private void show_data() {


        Query query = mDatabase.child("studenttranscript");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Data Reterive
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    StudnetApplyForTranscriptModel studentFormModel = new StudnetApplyForTranscriptModel();

                    studentFormModel.setCINC(dataSnapshot.child("cinc").getValue().toString());
                    //  studentFormModel.setSt_name(dataSnapshot.child("st_dec").getValue().toString());



                    // For Intent

                    arrayList.add(studentFormModel);

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(AdminStudentTranscriptActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}