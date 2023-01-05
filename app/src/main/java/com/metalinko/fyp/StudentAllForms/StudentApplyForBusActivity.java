package com.metalinko.fyp.StudentAllForms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metalinko.fyp.Model.StudentBusModel;
import com.metalinko.fyp.databinding.ActivityStudentApplyForBusBinding;

public class StudentApplyForBusActivity extends AppCompatActivity {
        ActivityStudentApplyForBusBinding binding;

    private String Full_Name, System_id , Course, Samester , Shift, Challan_Form , Bus_No , Shop_Number;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private StudentBusModel studentBusModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentApplyForBusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
      // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("studentbus");




        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Full_Name = binding.edtname.getText().toString();
                System_id = binding.edtstid.getText().toString();
                Course = binding.stdcource.getText().toString();
                Samester = binding.stdsemester.getText().toString();
                Challan_Form = binding.stdBuschallan.getText().toString();
                Bus_No = binding.stdbusno.getText().toString();
                Shop_Number = binding.edtstopname.getText().toString();

                if(Full_Name.isEmpty()){

                    binding.edtname.setError("Enter Name");

                }else if(System_id.isEmpty()){
                    binding.edtstid.setError("Enter System ID");

                }else if(Course.isEmpty()){
                    binding.stdcource.setError("Enter Course");
                }

                else if(Samester.isEmpty())
                {
                    binding.stdsemester.setError("Enter Samester");
                }

                else if(Challan_Form.isEmpty()){
                    binding.stdBuschallan.setError("Enter Samester");

                }

                else if(Shop_Number.isEmpty()){

                    binding.edtstopname.setError("Enter Bus Number");
                }

                else
                {

                    StudentBusModel studentBusModel = new StudentBusModel(


                            Full_Name, System_id , Course, Samester , Shift, Challan_Form , Bus_No , Shop_Number

                    );


                    mDatabase.child(System_id).setValue(studentBusModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {

                                Toast.makeText(StudentApplyForBusActivity.this, "Competed", Toast.LENGTH_SHORT).show();
                                //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                                //    startActivity(intent);


                                binding.edtname.setText("");
                                binding.edtstid.setText("");
                                binding.stdcource.setText("");
                                binding.stdsemester.setText("");
                                binding.stdBuschallan.setText("");
                                binding.stdbusno.setText("");
                                binding.edtstopname.setText("");
                            }
                            else
                            {
                                Toast.makeText(StudentApplyForBusActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(StudentApplyForBusActivity.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });



                }













            }
        });








    }
}