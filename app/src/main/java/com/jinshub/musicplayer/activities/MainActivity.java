package com.jinshub.musicplayer.activities;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.jinshub.musicplayer.R;
import com.jinshub.musicplayer.adapters.ScreenSlidePagerAdapter;
import com.jinshub.musicplayer.databinding.ActivityMainBinding;
import com.jinshub.musicplayer.fragments.MusicPlayerFragment;
import com.jinshub.musicplayer.fragments.PlaylistFragment;
import com.jinshub.musicplayer.models.Music;
import com.jinshub.musicplayer.viewmodels.MusicViewModel;

public class MainActivity extends AppCompatActivity {
    // View binding for the main activity layout
    ActivityMainBinding mainBinding;

    // Tag for logging messages
    private static final String LOG_TAG = "MainActivity";

    // View pager for the main activity
    private ViewPager2 viewPager;

    // View model for the music player fragment
    private MusicViewModel musicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Creating MainActivity");

        super.onCreate(savedInstanceState);
        
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        initDefaultNightMode();

        viewPager = mainBinding.pager;
        viewPager.setAdapter(new ScreenSlidePagerAdapter(this));

        musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        musicViewModel.getSelectedSong().observe(this, new Observer<Music>() {
            @Override
            public void onChanged(Music song) {
                 // switch to MusicPlayerFragment when a song is selected
                viewPager.setCurrentItem(0);
            }
        });
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