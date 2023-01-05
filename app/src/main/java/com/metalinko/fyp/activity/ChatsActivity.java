package com.metalinko.fyp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.metalinko.fyp.Model.LoggedInUserModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.db.DatabaseRepository;

public class ChatsActivity extends AppCompatActivity {
    DatabaseRepository databaseRepository;
    Context context;
    LoggedInUserModel loggedInUserModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        context = this;
        databaseRepository = new DatabaseRepository(context);
        bind_views();
        get_logged_in_user();

    }

    private void get_logged_in_user() {
        databaseRepository.get_logged_in_user().observe((LifecycleOwner) context, new Observer<LoggedInUserModel>() {
            @Override
            public void onChanged(LoggedInUserModel u) {
                if (u == null) {
                    Toast.makeText(context, "You are not logged in.", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                loggedInUserModel = u;
            }
        });
    }

    private void bind_views() {
        ((CardView) (findViewById(R.id.btn_logout))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_user();
            }
        });
        ((CardView) (findViewById(R.id.btn_pick_user))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick_user();
            }
        });
    }

    int my_counter = 0;
    Handler handler = new Handler();

    private void my_counter() {
        ((Button) (findViewById(R.id.btn_pick_user))).setText(my_counter + "");
        my_counter++;
        handler.postDelayed(new Runnable() {
            public void run() {
                my_counter();
                // yourMethod();
            }
        }, 1000);
    }

    private void pick_user() {
        Intent i = new Intent(context, UsersActivity.class);
        i.putExtra("task", "pick_user");
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data == null) {
                Toast.makeText(context, "Data response is null", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.hasExtra("user_id")) {
                receiver_id = data.getStringExtra("user_id");
                if (receiver_id != null) {
                    if (!receiver_id.isEmpty()) {
                        if ((loggedInUserModel.user_id + "").equals(receiver_id)) {
                            Toast.makeText(context, "You cannot send message to yourself.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onActivityResult: You cannot send message to yourself. ");
                            return;
                        }
                        Intent intent = new Intent(context, ChatActivity.class);
                        intent.putExtra("sender_id", (loggedInUserModel.user_id + ""));
                        intent.putExtra("receiver_id", receiver_id+"");
                        context.startActivity(intent);
                    }
                }
            }
        }
    }

    private static final String TAG = "ChatsActivity";
    String receiver_id = "";


    private void logout_user() {
        try {
            databaseRepository.logout_user();
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            finish();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to logout because " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}