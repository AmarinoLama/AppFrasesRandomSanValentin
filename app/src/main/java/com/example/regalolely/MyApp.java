package com.example.regalolely;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Always in light mode
    }
}