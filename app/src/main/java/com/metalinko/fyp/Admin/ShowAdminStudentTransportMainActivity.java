package com.metalinko.fyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.databinding.ActivityShowAdminStudentTransportMainBinding;

public class ShowAdminStudentTransportMainActivity extends AppCompatActivity {
        ActivityShowAdminStudentTransportMainBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowAdminStudentTransportMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("studenttranscript");




        String id = getIntent().getStringExtra("key");


        mDatabase.orderByChild("cinc").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {




                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {



                    binding.edtcnic.setText(dataSnapshot.child("cinc").getValue().toString());
                    binding.edtstdname.setText(dataSnapshot.child("contact_Number").getValue().toString());
                    binding.stdfname.setText(dataSnapshot.child("father_Name").getValue().toString());
                    binding.edtstdrollno.setText(dataSnapshot.child("roll_No").getValue().toString());
                    binding.edtstdcourse.setText(dataSnapshot.child("course").getValue().toString());
                    binding.edtstdnationality.setText(dataSnapshot.child("nationality").getValue().toString());
                    binding.stddeparment.setText(dataSnapshot.child("department").getValue().toString());
                    binding.edtDOB.setText(dataSnapshot.child("dob").getValue().toString());
                    binding.secTitle.setText(dataSnapshot.child("session").getValue().toString());
                    binding.stdLastExam.setText(dataSnapshot.child("last_Exam_Held").getValue().toString());
                    binding.stdregno.setText(dataSnapshot.child("registration_Number").getValue().toString());
                    binding.summerattended.setText(dataSnapshot.child("summer_Attend").getValue().toString());
                    binding.semesterrepeat.setText(dataSnapshot.child("summer_Repeat").getValue().toString());
                    binding.subimprove.setText(dataSnapshot.child("subject_Improved").getValue().toString());
                    binding.language.setText(dataSnapshot.child("foregin_Language").getValue().toString());
                    binding.special.setText(dataSnapshot.child("specialization").getValue().toString());
                    binding.semesetertosemester.setText(dataSnapshot.child("semester").getValue().toString());
                    binding.CeasedStudent.setText(dataSnapshot.child("current_Semester").getValue().toString());
                 binding.contactNo.setText(dataSnapshot.child("contact_Number").getValue().toString());
                   binding.postal.setText(dataSnapshot.child("postal_Address").getValue().toString());





                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(ShowAdminStudentTransportMainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });





    }
}