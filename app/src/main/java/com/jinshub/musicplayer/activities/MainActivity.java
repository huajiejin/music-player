package com.jinshub.musicplayer.activities;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.jinshub.musicplayer.R;
import com.jinshub.musicplayer.adapters.ScreenSlidePagerAdapter;
import com.jinshub.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // View binding for the main activity layout
    ActivityMainBinding mainBinding;

    // Tag for logging messages
    private static final String LOG_TAG = "MainActivity";

    // View pager for the main activity
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Creating MainActivity");

        super.onCreate(savedInstanceState);
        
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        initDefaultNightMode();

        viewPager = mainBinding.pager;
        viewPager.setAdapter(new ScreenSlidePagerAdapter(this));
    }

    // Set the theme to dark mode if the user has enabled it (saved in SharedPreferences),
    // If the user has not enabled dark mode, the default theme is light mode
    private void initDefaultNightMode() {
        if (getSharedPreferences("settings", MODE_PRIVATE).getBoolean("darkMode", false)) {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}