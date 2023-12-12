package com.jinshub.musicplayer.activities;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.jinshub.musicplayer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}