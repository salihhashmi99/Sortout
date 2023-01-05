package com.metalinko.fyp.SecurityGardForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metalinko.fyp.Admin.AdminStudentComplaintActivity;
import com.metalinko.fyp.Adopter.AdminStudentAdopter;
import com.metalinko.fyp.Adopter.SecurityGardCallAdopter;
import com.metalinko.fyp.Model.SecurityGardCallModel;
import com.metalinko.fyp.Model.StudentFormModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.databinding.ActivitySecurityGardCallShowRecyclerBinding;

import java.util.ArrayList;

public class SecurityGardCallShowRecyclerActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;
            ActivitySecurityGardCallShowRecyclerBinding binding;
    public ArrayList<SecurityGardCallModel> arrayList;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private SecurityGardCallAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecurityGardCallShowRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new SecurityGardCallAdopter(arrayList, this);
        recyclerView.setAdapter(adapter);

        show_data();



    }


    private void show_data() {


        Query query = mDatabase.child("securitycall");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Data Reterive
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    SecurityGardCallModel studentFormModel = new SecurityGardCallModel();

                    studentFormModel.setSd_name(dataSnapshot.child("sd_name").getValue().toString());
                    studentFormModel.setPhone_number(dataSnapshot.child("phone_number").getValue().toString());
                    //  studentFormModel.setSt_name(dataSnapshot.child("st_dec").getValue().toString());



                    // For Intent

                    arrayList.add(studentFormModel);

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(SecurityGardCallShowRecyclerActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("WrongViewCast")
    public void call(View view) {
        mEditTextNumber = findViewById(R.id.imageView3);
        makePhoneCall();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5358234"));
        startActivity(callIntent);

    }

    private void makePhoneCall() {
        String number = mEditTextNumber.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(SecurityGardCallShowRecyclerActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SecurityGardCallShowRecyclerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(SecurityGardCallShowRecyclerActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}