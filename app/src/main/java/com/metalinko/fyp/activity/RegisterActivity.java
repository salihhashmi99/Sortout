package com.metalinko.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.metalinko.fyp.Model.LoggedInUserModel;
import com.metalinko.fyp.Model.ResponseModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.db.DatabaseRepository;
import com.metalinko.fyp.web.WebInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.metalinko.fyp.activity.StartActivity.BASE_URL;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        databaseRepository = new DatabaseRepository(context);
        bind_views();
    }

    Context context;
    TextInputEditText name_view;
    TextInputEditText phone_number_view;
    Button register_button;
    TextView status;
    DatabaseRepository databaseRepository;
    ProgressDialog progressDialog;

    private void bind_views() {
        name_view = findViewById(R.id.name_view);
        phone_number_view = findViewById(R.id.phone_number_view);
        register_button = findViewById(R.id.register_button);
        status = findViewById(R.id.data);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_user();
            }
        });

        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                context.startActivity(intent);
            }
        });

    }

    String name_value = "";
    String phone_value = "";

    private void register_user() {
        name_value = name_view.getText().toString();
        if (name_value.length() < 4) {
            Toast.makeText(this, "Enter valid full name.", Toast.LENGTH_SHORT).show();
            name_view.requestFocus();
            return;
        }

        phone_value = phone_number_view.getText().toString();
        if (phone_value.length() < 5) {
            Toast.makeText(this, "Enter valid phone number.", Toast.LENGTH_SHORT).show();
            phone_number_view.requestFocus();
            return;
        }

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        status.setText("Loading....");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //step 1
        Retrofit ret = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Step 2
        WebInterface webInterface = ret.create(WebInterface.class);

        //Step 3
        Call<ResponseModel> my_call = webInterface.register(
                name_value,
                phone_value
        );

        my_call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressDialog.hide();
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    status.setText("Success BUT " + response.errorBody());
                    return;
                }

                if (response.code() == 0) {
                    Toast.makeText(RegisterActivity.this, "Failed to create account because " + response.message(), Toast.LENGTH_SHORT).show();
                    status.setText("Failed to create account because " + response.message());
                    return;
                }

                LoggedInUserModel loggedInUserModel = new Gson().fromJson(response.body().data, LoggedInUserModel.class);

                status.setText("SUCCESS ! " + loggedInUserModel.name);

                try {

                    databaseRepository.login_user(loggedInUserModel);
                    Intent i = new Intent(context, ChatsActivity.class);
                    context.startActivity(i);
                    finish();

                } catch (Exception e) {
                    status.setText("Failed to login user because " + e.getMessage());
                    Toast.makeText(context, "Failed to login user because " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.hide();
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                status.setText("FAILED: " + t.getMessage());
            }
        });


    }
}