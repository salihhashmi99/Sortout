package com.metalinko.fyp.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import static com.metalinko.fyp.activity.StartActivity.LOGGED_IN_USER_TABLE;


@Entity(tableName = LOGGED_IN_USER_TABLE)
public class LoggedInUserModel {

    @NonNull
    @PrimaryKey
    public int user_id = 0;

    public String name = "";
    public String phone_number = "";
    public String reg_date = "";
    public String last_seen = "";
    public String profile_photo = "";
}
