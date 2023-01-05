package com.metalinko.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.metalinko.fyp.Model.LoggedInUserModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.db.DatabaseRepository;

public class StartActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "mylisteo_whatsapp";
    public static final String LOGGED_IN_USER_TABLE = "login";
    public static final String USERS_TABLE = "user";
    public static final String MESSAGES_TABLE = "message";
    public static final String BASE_URL = "https://salih.mylisteo.com";
    DatabaseRepository databaseRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        databaseRepository = new DatabaseRepository(this);

        check_login();
    }

    private void check_login() {
        databaseRepository.get_logged_in_user().observe(this, new Observer<LoggedInUserModel>() {
            @Override
            public void onChanged(LoggedInUserModel loggedInUserModel) {
                if (loggedInUserModel == null) {
                    Toast.makeText(StartActivity.this, "Not Logged in", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(StartActivity.this, RegisterActivity.class);
                    StartActivity.this.startActivity(i);
                    finish();
                    return;
                } else {

                    databaseRepository. login_user(loggedInUserModel);
                    Intent i = new Intent(StartActivity.this, ChatsActivity.class);
                    StartActivity.this.startActivity(i);
                    finish();
                }
            }
        });
    }
}