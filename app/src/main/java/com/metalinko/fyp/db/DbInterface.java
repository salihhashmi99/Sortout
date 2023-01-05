package com.metalinko.fyp.db;

import static com.metalinko.fyp.activity.StartActivity.LOGGED_IN_USER_TABLE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.metalinko.fyp.Model.ChatMessage;
import com.metalinko.fyp.Model.LoggedInUserModel;
import com.metalinko.fyp.Model.UserModel;

import java.util.List;
import static com.metalinko.fyp.activity.StartActivity.LOGGED_IN_USER_TABLE;
import static com.metalinko.fyp.activity.StartActivity.MESSAGES_TABLE;
import static com.metalinko.fyp.activity.StartActivity.USERS_TABLE;

@Dao
public interface DbInterface {

    @Query("SELECT * FROM " + LOGGED_IN_USER_TABLE)
    LiveData<LoggedInUserModel> get_logged_in_user();

    @Insert
    void login_user(LoggedInUserModel loggedInUserModel);


    @Query("DELETE FROM " + LOGGED_IN_USER_TABLE + " WHERE 1")
    void logout_user();


    @Query("SELECT * FROM " + USERS_TABLE + " WHERE user_id = :user_id")
    UserModel get_user(int user_id);

    @Query("SELECT * FROM " + MESSAGES_TABLE + " WHERE message_id = :message_id")
    UserModel get_message(int message_id);

    @Query("SELECT * FROM " + USERS_TABLE + " ORDER BY name ASC")
    LiveData<List<UserModel>> get_users();


    @Query("SELECT * FROM " + MESSAGES_TABLE + " WHERE ((sender = :sender AND receiver = :receiver) OR(sender = :receiver AND receiver = :sender) ) ORDER BY message_id ASC")
    LiveData<List<ChatMessage>> get_chat(int sender, int receiver);


    @Query("SELECT * FROM " + USERS_TABLE + " WHERE user_id = :user_id")
    LiveData<UserModel> get_user_by_id(int user_id);


    @Insert
    void save_message(ChatMessage chatMessage);


    @Insert
    void save_user(UserModel userModel);

    @Update
    void update_user(UserModel userModel);

    @Update
    void update_message(ChatMessage chatMessage);


}