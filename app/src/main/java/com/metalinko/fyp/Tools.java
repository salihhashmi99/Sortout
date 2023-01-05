package com.metalinko.fyp;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools{

    public static void setSystemBarColorInt(Activity act, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public static long getTimeStamp(){
        return System.currentTimeMillis() ;
    }

    public static String toTimeAgo(String t) {
        if (t == null) {
            return "00:00";
        }
        if (t.length() < 4) {
            return "00:00";
        }
        Long time;
        try {
            time = Long.valueOf(t);
        } catch (Exception e) {
            return "00:00";
        }
        if (time < 1000) {
            return "";
        }

        Long now = System.currentTimeMillis() / 1000;
        if ((now - time) < 60) {
            return "just now";
        } else if ((now - time) < (60 * 60)) {
            return ((now - time) / 60) + "m ago";
        } else if ((now - time) < (60 * 60 * 24)) {
            SimpleDateFormat newFormat = new SimpleDateFormat("h:mm a");
            return newFormat.format(new Date(time * 1000));
        } else if ((now - time) < (60 * 60 * 24 * 2)) {
            return "yesterday";
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, dd MMM YYYY");
        return newFormat.format(new Date(time * 1000));
    }

    private static final String TAG = "ToolsTools";


    public static String toTime(String t) {
        if (t == null) {
            return "00:00";
        }
        if (t.length() < 4) {
            return "00:00";
        }
        Long time;
        try {
            time = Long.valueOf(t);
        } catch (Exception e) {
            return "00:00";
        }
        if (time < 1000) {
            return "";
        }


        SimpleDateFormat newFormat = new SimpleDateFormat("h:mm a");
        return newFormat.format(new Date((time * 1000)));
    }


}
