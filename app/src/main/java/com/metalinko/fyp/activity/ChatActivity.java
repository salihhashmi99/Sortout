package com.metalinko.fyp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.metalinko.fyp.Adopter.AdapterChatWhatsapp;
import com.metalinko.fyp.Model.ChatMessage;
import com.metalinko.fyp.Model.ResponseModel;
import com.metalinko.fyp.Model.UserModel;
import com.metalinko.fyp.R;
import com.metalinko.fyp.db.DatabaseRepository;
import com.metalinko.fyp.web.WebInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.metalinko.fyp.Tools.getTimeStamp;
import static com.metalinko.fyp.Tools.toTimeAgo;
import static com.metalinko.fyp.activity.StartActivity.BASE_URL;

public class ChatActivity extends AppCompatActivity {
    DatabaseRepository databaseRepository;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        context = this;
        databaseRepository = new DatabaseRepository(context);
       // initToolbar();
        iniComponent();
        get_users_data();
    }

    UserModel receiver = new UserModel();

    private void get_users_data() {
        if (
                (!getIntent().hasExtra("sender_id")) || (!getIntent().hasExtra("receiver_id"))
        ) {
            Toast.makeText(this, "Sender or receiver ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        sender_id = getIntent().getStringExtra("sender_id");
        receiver_id = getIntent().getStringExtra("receiver_id");
        get_local_data();

        databaseRepository.get_user_by_id(Integer.valueOf(receiver_id)).observe((LifecycleOwner) context, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                if (userModel == null) {
                    Toast.makeText(context, "Receiver not found on local db", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                receiver = userModel;
                update_ui();


            }
        });


    }

    String last_seen = "";

    private void update_ui() {
        ((TextView) (findViewById(R.id.receiver_name))).setText(receiver.name + "");
        last_seen = toTimeAgo(receiver.last_seen);
        if (last_seen.equals("just now")) {
            last_seen = "online";
        }
        ((TextView) (findViewById(R.id.receiver_last_seen))).setText(last_seen + "");

/*        toolbar.setTitle(receiver.name);
        toolbar.setSubtitle(receiver.last_seen);*/
    }

    String sender_id = null;
    String receiver_id = null;
    RecyclerView recycler_view;
    AdapterChatWhatsapp adapter;

    private void iniComponent() {
        recycler_view = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setHasFixedSize(true);
        adapter = new AdapterChatWhatsapp(this);
        recycler_view.setAdapter(adapter);

        btn_send = findViewById(R.id.btn_send);
        et_content = findViewById(R.id.text_content);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChat();
            }
        });

        et_content.addTextChangedListener(contentWatcher);

        (findViewById(R.id.lyt_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void get_local_data() {
        databaseRepository.get_chat(Integer.valueOf(sender_id), Integer.valueOf(receiver_id)).observe((LifecycleOwner) context, new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(List<ChatMessage> chatMessages) {
                if (chatMessages == null) {
                    return;
                }
                if (!chatMessages.isEmpty()) {
                    min_time = chatMessages.get((chatMessages.size() - 1)).last_update + "";
                }
                if (min_time.length() < 2) {
                    min_time = "0";
                }
                if (!chats_initialized) {
                    getChats();
                }


                adapter.updateItems(chatMessages);
                recycler_view.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        hideKeyboard();
    }

    boolean chats_initialized = false;

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private TextWatcher contentWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable etd) {
            if (etd.toString().trim().length() == 0) {
                btn_send.setImageResource(R.drawable.ic_mic);
            } else {
                btn_send.setImageResource(R.drawable.ic_send);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };


    //step 1
    Retrofit ret = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //Step 2
    WebInterface webInterface = ret.create(WebInterface.class);

    private void getChats() {
        if (chats_initialized) {
            return;
        }
        chats_initialized = true;
        //Step 3
        Call<List<ChatMessage>> chat_call = webInterface.get_chats(
                sender_id,
                min_time
        );
        chat_call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response == null) {
                    return;
                }
                if (!response.isSuccessful()) {
                    return;
                }
                Log.i(TAG, "on___Response: FOUND ==> " + response.body().size() + " chats");
                if (response.body().isEmpty()) {
                    return;
                }
                databaseRepository.save_messages(response.body());
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {

            }
        });
    }

    String min_time = "0";

    private void sendChat() {
        final String msg = et_content.getText().toString();
        if (msg.isEmpty()) return;


        //Step 3
        Call<ResponseModel> my_call = webInterface.send_message(
                sender_id,
                receiver_id,
                msg
        );
        my_call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Gson gson = new Gson();
                if (!response.isSuccessful()) {
                    ChatMessage chatMessage = gson.fromJson(response.body().data, ChatMessage.class);
                    chatMessage.fromMe = true;
                    chatMessage.receive_time = "not_sent";
                    chatMessage.body = msg;
                    chatMessage.sender = sender_id;
                    chatMessage.receiver = receiver_id;
                    chatMessage.sent_time = getTimeStamp() + "";
                    List<ChatMessage> tempList = new ArrayList<>();
                    tempList.add(chatMessage);
                    databaseRepository.save_messages(tempList);
                    return;
                }
                if (response.body().code == 0) {
                    ChatMessage chatMessage = gson.fromJson(response.body().data, ChatMessage.class);
                    chatMessage.fromMe = true;
                    chatMessage.body = msg;
                    chatMessage.sender = sender_id;
                    chatMessage.receiver = receiver_id;
                    chatMessage.sent_time = getTimeStamp() + "";
                    chatMessage.receive_time = "not_sent";
                    List<ChatMessage> tempList = new ArrayList<>();
                    tempList.add(chatMessage);
                    databaseRepository.save_messages(tempList);
                    return;
                }

                try {
                    ChatMessage chatMessage = gson.fromJson(response.body().data, ChatMessage.class);
                    chatMessage.fromMe = true;
                    List<ChatMessage> tempList = new ArrayList<>();
                    tempList.add(chatMessage);
                    databaseRepository.save_messages(tempList);

                } catch (Exception e) {
                    Log.d(TAG, "onResponse: FAILED BECAUSE " + e.getMessage());
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.fromMe = true;
                chatMessage.receive_time = "not_sent";
                chatMessage.body = msg;
                chatMessage.sender = sender_id;
                chatMessage.receiver = receiver_id;
                chatMessage.sent_time = getTimeStamp() + "";
                List<ChatMessage> tempList = new ArrayList<>();
                tempList.add(chatMessage);
                databaseRepository.save_messages(tempList);
                return;
            }
        });



/*        adapter.insertItem(new ChatMessage(
                adapter.getItemCount(),
                Tools.getFormattedTimeEvent(System.currentTimeMillis()),
                msg,
                true,
                adapter.getItemCount() % 5 == 0)
        );*/

        Log.d(TAG, "sendChat: ");
        et_content.setText("");
        recycler_view.scrollToPosition(adapter.getItemCount() - 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*                adapter.insertItem(new ChatMessage(
                        adapter.getItemCount(),
                        Tools.getFormattedTimeEvent(System.currentTimeMillis()),
                        msg,
                        false,
                        adapter.getItemCount() % 5 == 0
                ));*/
                recycler_view.scrollToPosition(adapter.getItemCount() - 1);
            }
        }, 1000);
    }

    private static final String TAG = "ChatActivity";
    private ActionBar actionBar;
    private FloatingActionButton btn_send;
    private EditText et_content;
 //   Toolbar toolbar;


}