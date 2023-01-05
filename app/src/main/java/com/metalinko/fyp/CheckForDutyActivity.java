package com.metalinko.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.metalinko.fyp.databinding.ActivityCheckForDutyBinding;

public class CheckForDutyActivity extends AppCompatActivity {
        ActivityCheckForDutyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckForDutyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        WebView webView = findViewById(R.id.webview);

        webView.loadUrl("https://mylisteo.com/displayTimetable.php");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());











    }
}