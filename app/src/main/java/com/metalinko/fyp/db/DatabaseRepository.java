package com.metalinko.fyp.db;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.metalinko.fyp.Model.ChatMessage;
import com.metalinko.fyp.Model.LoggedInUserModel;
import com.metalinko.fyp.Model.UserModel;

import java.util.List;
public class DatabaseRepository {
    Context context;
    public Database database;
    public DbInterface dbInterface;
    private static final String TAG = "DatabaseRepository";

    public DatabaseRepository(Context context) {
        this.context = context;
        database = Database.getGetInstance(context);
        dbInterface = database.dbInterface();
    }

    public void logout_user() {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dbInterface.logout_user();
                    Log.d(TAG, "run: Success logged out successfully!...");
                } catch (Exception e) {
                    Log.d(TAG, "run: Failed to logout user because...");
                }
            }
        });
    }


    public void save_messages(List<ChatMessage> messages) {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                for (ChatMessage m : messages) {
                    UserModel local_msg = dbInterface.get_message(m.message_id);
                    if (local_msg == null) {
                        try {
                            dbInterface.save_message(m);
                            Log.d(TAG, "run:  INSERTED MESSAGE " + m.body + " success fullly");
                        } catch (Exception e) {
                            Log.d(TAG, "run:  Failed to INSTERT USER " + m.body + " BECAUSE " + e.getMessage());
                        }
                    } else {
                        try {
                            dbInterface.update_message(m);
                            Log.d(TAG, "run:  UPDATED message " + m.body + " success fullly");
                        } catch (Exception e) {
                            Log.d(TAG, "run:  Failed to UPDATE MESSA  " + m.body + " BECAUSE " + e.getMessage());
                        }

                    }
                }
            }
        });
    }


    public void save_users(List<UserModel> users) {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                for (UserModel u : users) {
                    UserModel local_user = dbInterface.get_user(u.user_id);

                    if (local_user == null) {

                        try {
                            dbInterface.save_user(u);
                            Log.d(TAG, "run:  INSERTED USER " + u.name + " success fullly");
                        } catch (Exception e) {
                            Log.d(TAG, "run:  Failed to INSTERT USER " + u.name + " BECAUSE " + e.getMessage());
                        }
                    } else {
                        try {
                            dbInterface.update_user(u);
                            Log.d(TAG, "run:  UPDATED USER " + u.name + " success fullly");
                        } catch (Exception e) {
                            Log.d(TAG, "run:  Failed to UPDATE USER " + u.name + " BECAUSE " + e.getMessage());
                        }

                    }
                }
            }
        });
    }

    public void login_user(LoggedInUserModel loggedInUserModel) {
        database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dbInterface.login_user(loggedInUserModel);
                    Log.d(TAG, "run: Success logged in successfully!...");
                } catch (Exception e) {
                    Log.d(TAG, "run: Failed to login user because...");
                }
            }
        });
    }

    public LiveData<LoggedInUserModel> get_logged_in_user() {
        return dbInterface.get_logged_in_user();
    }

    public LiveData<List<UserModel>> get_users() {
        return dbInterface.get_users();
    }

    public LiveData<List<ChatMessage>> get_chat(int sender,int receiver) {
        return dbInterface.get_chat(sender,receiver);
    }

    public LiveData<UserModel> get_user_by_id(int user_id) {
        return dbInterface.get_user_by_id(user_id);
    }


}
