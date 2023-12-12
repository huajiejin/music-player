package com.jinshub.musicplayer.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jinshub.musicplayer.fragments.MusicPlayerFragment;
import com.jinshub.musicplayer.fragments.PlaylistFragment;
import com.jinshub.musicplayer.fragments.SettingsFragment;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    public ScreenSlidePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MusicPlayerFragment();
            case 1:
                return new PlaylistFragment();
            case 2:
                return new SettingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
