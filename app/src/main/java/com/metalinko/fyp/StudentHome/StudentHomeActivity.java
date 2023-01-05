package com.metalinko.fyp.StudentHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metalinko.fyp.Adopter.SliderAdapter;
import com.metalinko.fyp.R;
import com.metalinko.fyp.StudentAllForms.StudentApplyForBusActivity;
import com.metalinko.fyp.StudentAllForms.StudentApplyForTranscriptActivity;
import com.metalinko.fyp.StudentAllForms.StudentComplaintActivity;
import com.metalinko.fyp.SuggestionForm;
import com.metalinko.fyp.activity.RegisterActivity;
import com.metalinko.fyp.databinding.ActivityStudentHomeBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class StudentHomeActivity extends AppCompatActivity {
        ActivityStudentHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        SliderView sliderView;
        int[] images = {R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.one,
                R.drawable.two,
                R.drawable.three};
        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        binding.stdmaincomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , StudentComplaintActivity.class);
                startActivity(intent);



            }
        });





        binding.stdtranscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , StudentApplyForTranscriptActivity.class);
                startActivity(intent);
            }
        });


        binding.stdbuscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , StudentApplyForBusActivity.class);
                startActivity(intent);
            }
        });




        binding.livechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext() , RegisterActivity.class);
                startActivity(intent);


            }
        });


        binding.stdsuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext() , SuggestionForm.class);
                startActivity(intent);
            }
        });

    }
}