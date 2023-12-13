package com.jinshub.musicplayer.activities;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;

import com.jinshub.musicplayer.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;

    // Tag for logging messages
    private static final String LOG_TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Creating SettingsActivity");

        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set the switch to the user's saved preference
        binding.darkModeSwitch.setChecked(getSharedPreferences("settings", MODE_PRIVATE).getBoolean("darkMode", false));

        binding.darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // set the theme to dark mode
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // set the theme to light mode
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            // save to SharedPreferences
            getSharedPreferences("settings", MODE_PRIVATE).edit().putBoolean("darkMode", isChecked).apply();
        });
    }
}