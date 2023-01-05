package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Adopter.AdminStudentAdopter;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.databinding.ActivityAdminStudentComplaintBinding;

import java.util.ArrayList;

public class AdminStudentComplaintActivity extends AppCompatActivity {
    ActivityAdminStudentComplaintBinding binding;
    public ArrayList<StudentFormModel> arrayList;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private AdminStudentAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminStudentComplaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new AdminStudentAdopter(arrayList, this);
        recyclerView.setAdapter(adapter);

        show_data();
    }

    private void show_data() {


        Query query = mDatabase.child("studentform");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Data Reterive
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    StudentFormModel studentFormModel = new StudentFormModel();

                    studentFormModel.setSt_name(dataSnapshot.child("st_system_id").getValue().toString());
                  //  studentFormModel.setSt_name(dataSnapshot.child("st_dec").getValue().toString());



                    // For Intent

                    arrayList.add(studentFormModel);

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(AdminStudentComplaintActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}