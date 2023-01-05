package com.metalinko.fyp.StudentAllForms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metalinko.fyp.Model.StudnetApplyForTranscriptModel;
import com.metalinko.fyp.databinding.ActivityStudentApplyForTranscriptBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentApplyForTranscriptActivity extends AppCompatActivity {
        ActivityStudentApplyForTranscriptBinding binding;
    private String CINC, Name, Father_Name, Roll_No , Course , Nationality, Department ,DOB ,Session , Last_Exam_Held,
            Shift, Registration_Number  , Summer_Attend,Summer_Repeat , Subject_Improved, Foregin_Language , Specialization
            , Semester , Current_Semester, Contact_Number, Postal_Address, Image_Transcript, Education_Documents
            , University_Clear;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentApplyForTranscriptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        // Data
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase =firebaseDatabase.getReference("studenttranscript");












        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CINC = binding.edtcnic.getText().toString();
                Name = binding.edtstdname.getText().toString();
                Father_Name = binding.stdfname.getText().toString();
                Roll_No =  binding.edtstdrollno.getText().toString();
                Course = binding.edtstdcourse.getText().toString();
                Nationality = binding.edtstdnationality.getText().toString();
                Department = binding.stddeparment.getText().toString();
                DOB = binding.edtDOB.getText().toString();
                Session = binding.secTitle.getText().toString();
                Last_Exam_Held = binding.stdLastExam.getText().toString();
                Shift = binding.morning.getText().toString();
                Registration_Number = binding.stdregno.getText().toString();
                Summer_Attend = binding.summerattended.getText().toString();
                Summer_Repeat = binding.semesterrepeat.getText().toString();
                Subject_Improved = binding.subimprove.getText().toString();
                Foregin_Language = binding.language.getText().toString();
                Specialization = binding.special.getText().toString();
                Semester = binding.semesetertosemester.getText().toString();
                Current_Semester =binding.CeasedStudent.getText().toString();
                Contact_Number = binding.contactNo.getText().toString();
                Postal_Address = binding.postal.getText().toString();


                if(CINC.isEmpty()){

                    binding.edtcnic.setError("Enter CNIC");
                }

                else if(Name.isEmpty()){

                    binding.edtstdname.setError("Enter Name");
                }
                else if(Father_Name.isEmpty())
                {

                    binding.stdfname.setError("Enter Father Name");
                }
                else if(Roll_No.isEmpty())
                {

                    binding.edtstdrollno.setError("Enter Roll No");
                }
                else if(Course.isEmpty())
                {

                    binding.edtstdcourse.setError("Enter Course");
                }
                else if(Nationality.isEmpty()){

                    binding.edtstdnationality.setError("Enter Nationality");
                }
                else if(Department.isEmpty()){


                    binding.stddeparment.setError("Enter Department");
                }

                else if(DOB.isEmpty()){

                    binding.edtDOB.setError("Enter DOB");
                }

                else if(Session.isEmpty())

                {
                    binding.secTitle.setError("Enter Session");
                }

                else if(Last_Exam_Held.isEmpty())
                {

                    binding.stdLastExam.setError("Enter Last Exam");
                }

                else if(Shift.isEmpty())
                {

                    binding.morning.setError("Select Shift");
                }

                else if(Registration_Number.isEmpty()){

                    binding.stdregno.setError("Enter Registration");
                }

                else if(Summer_Attend.isEmpty())

                {
                            binding.summerattended.setError("Enter Summer");
                }

                else if(Summer_Repeat.isEmpty()){


                    binding.semesterrepeat.setError("Enter Summer Repeat");
                }

                else if(Subject_Improved.isEmpty())
                {

                    binding.subimprove.setError("Enter Improved");
                }

                else if(Foregin_Language.isEmpty())
                {

                    binding.language.setError("Enter Language");
                }

                else if(Specialization.isEmpty())
                {
                    binding.special.setError("Enter Specialization ");
                }

                else if(Semester.isEmpty())
                {

                    binding.semesetertosemester.setError("Enter Semester");
                }

                else if(Current_Semester.isEmpty())
                {

                    binding.CeasedStudent.setError("Enter CeasedStudent");
                }

                else if(Contact_Number.isEmpty())
                {

                    binding.contactNo.setError("Enter Contact Number");
                }

                else if(Postal_Address.isEmpty())
                {
                    binding.postal.setError("Enter Postal Address");
                }

                else
                {


                    StudnetApplyForTranscriptModel studnetApplyForTranscriptModel = new StudnetApplyForTranscriptModel(


                            CINC, Name, Father_Name, Roll_No, Course, Nationality, Department, DOB, Session, Last_Exam_Held,
                            Shift, Registration_Number, Summer_Attend, Summer_Repeat, Subject_Improved, Foregin_Language, Specialization
                            , Semester, Current_Semester, Contact_Number, Postal_Address, Image_Transcript, Education_Documents
                            , University_Clear
                    );




                    mDatabase.child(Roll_No).setValue(studnetApplyForTranscriptModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {

                                Toast.makeText(StudentApplyForTranscriptActivity.this, "Completed", Toast.LENGTH_SHORT).show();


                                uploadImage();

                               binding.edtcnic.setText("");
                                binding.edtstdname.setText("");
                                binding.stdfname.setText("");
                                 binding.edtstdrollno.setText("");
                                binding.edtstdcourse.setText("");
                              binding.edtstdnationality.setText("");;
                                binding.stddeparment.setText("");
                               binding.edtDOB.setText("");
                                binding.secTitle.setText("");
                                binding.stdLastExam.setText("");
                                binding.morning.setText("");
                                binding.stdregno.setText("");
                                binding.summerattended.setText("");
                                binding.semesterrepeat.setText("");
                                binding.subimprove.setText("");
                               binding.language.setText("");
                                binding.special.setText("");
                               binding.semesetertosemester.setText("");
                               binding.CeasedStudent.setText("");
                                binding.contactNo.setText("");
                               binding.postal.setText("");
                                //      Intent intent = new Intent(getApplicationContext() , Wardnar_access.class);
                                //    startActivity(intent);

                                /*



                                binding.sdName.setText("");
                                binding.edtstdname.setText("");
                                binding.sdEmail.setText("");
                                binding.edContact.setText("");
                                binding.secTitle.setText("");
                                binding.sdDec.setText("");

                                 */
                            }
                            else
                            {
                                Toast.makeText(StudentApplyForTranscriptActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(StudentApplyForTranscriptActivity.this, ": "+e, Toast.LENGTH_SHORT).show();
                        }
                    });





                }
















            }
        });




    }

    private void uploadImage() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        binding.firebaseimage.setImageURI(null);
                        Toast.makeText(StudentApplyForTranscriptActivity.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(StudentApplyForTranscriptActivity.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


                    }
                });
    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            binding.firebaseimage.setImageURI(imageUri);


        }
    }
}