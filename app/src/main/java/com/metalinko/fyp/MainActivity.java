package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.metalinko.fyp.Home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar pb;

        Handler handler = new Handler();
        progress();
        if (!isConnected(this)) {

            Intent intent = new Intent(getApplicationContext() , no_internet.class);
            startActivity(intent);
            finish();
        }
        else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(getApplicationContext() , slider_one.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);

        }
    }
    public void progress()
    {
        pb= (ProgressBar)findViewById(R.id.progressBar);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if (counter == 100)
                {
                    t.cancel();
                }
            }
        };
        t.schedule(tt,0,30);
    }
    private boolean isConnected(MainActivity mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }
}