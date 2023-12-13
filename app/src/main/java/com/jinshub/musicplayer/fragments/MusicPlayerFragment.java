package com.jinshub.musicplayer.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinshub.musicplayer.R;
import com.jinshub.musicplayer.databinding.FragmentMusicPlayerBinding;
import com.jinshub.musicplayer.models.Music;
import com.jinshub.musicplayer.utils.MusicUtil;
import com.jinshub.musicplayer.viewmodels.MusicViewModel;

public class MusicPlayerFragment extends Fragment {
    private FragmentMusicPlayerBinding binding;
    // Tag for logging messages
    private static final String LOG_TAG = "MusicPlayerFragment";
    private MusicViewModel musicViewModel;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Creating MusicPlayerFragment");
        binding = FragmentMusicPlayerBinding.inflate(inflater, container, false);

        musicViewModel = new ViewModelProvider(requireActivity()).get(MusicViewModel.class);


        Music currentSong = null;
        // Restore state
        if (savedInstanceState != null) {
            boolean isPlaying = savedInstanceState.getBoolean("isPlaying");
            int selectedSongIndex = savedInstanceState.getInt("selectedSongIndex");
            int currentPosition = savedInstanceState.getInt("currentPosition");
            Log.i(LOG_TAG, "Restoring state: isPlaying="+ isPlaying +", currentPosition="+ currentPosition);
            if (isPlaying && selectedSongIndex > -1) {
                currentSong = musicViewModel.getSongs().get(selectedSongIndex);
            }
        }

        if (currentSong != null) {
            musicViewModel.currentSong = currentSong;
        }

        musicViewModel.getSelectedSong().observe(getViewLifecycleOwner(), new Observer<Music>() {
            @Override
            public void onChanged(Music song) {
                // play the selected song
                Log.i(LOG_TAG, "Selected song changed to "+ song.getTitle());
                playSong(song);
            }
        });

        // if a song is already selected, display it
        if (musicViewModel.currentSong != null) {
            displaySong(musicViewModel.currentSong);
            binding.playPauseButton.setImageResource(R.drawable.ic_play);
        }

        // if a song is playing, display the pause button
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            binding.playPauseButton.setImageResource(R.drawable.ic_pause);
        }

        // set up the buttons to play the previous song, next song, and play/pause the current song
        binding.prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "prevButton clicked");
                musicViewModel.selectPrevSong();
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "nextButton clicked");
                musicViewModel.selectNextSong();
            }
        });

        binding.playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "playButton clicked");
                togglePlayingSong(musicViewModel.currentSong);
            }
        });

        return binding.getRoot();
    }

    // Start playing the music
    public void playSong(Music song) {
        Log.i(LOG_TAG, "playSong("+ song.getTitle() +")");
        if (song == null) {
            Log.i(LOG_TAG, "song is null");
            return;
        }
        if (song != musicViewModel.currentSong) {
            musicViewModel.currentSong = song;
            displaySong(song);
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            // stop the current song
            mediaPlayer.stop();
            binding.playPauseButton.setImageResource(R.drawable.ic_play);
        }
        int resourceId = song.getMusicResourceId();
        mediaPlayer = MediaPlayer.create(getActivity(), resourceId);

        mediaPlayer.start();
        binding.playPauseButton.setImageResource(R.drawable.ic_pause);
    }
    
    // Pause the music if it is playing, or play the music if it is paused
    public void togglePlayingSong(Music song) {
        if (song == null) {
            return;
        }
        Log.i(LOG_TAG, "togglePlayingSong("+ song.getTitle() +")");
        if (song == null) {
            Log.i(LOG_TAG, "song is null");
            return;
        }
        if (song != musicViewModel.currentSong) {
            musicViewModel.currentSong = song;
            displaySong(song);
        }
        if (mediaPlayer == null) {
            playSong(song);
        } else if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
        if (mediaPlayer.isPlaying()) {
            binding.playPauseButton.setImageResource(R.drawable.ic_pause);
        } else {
            binding.playPauseButton.setImageResource(R.drawable.ic_play);
        }
    }

    // Display the song's title, album art, and artist name
    public void displaySong(Music song) {
        binding.songTitle.setText(song.getTitle());
        binding.albumArt.setImageResource(song.getAlbumArtResourceId());
        // binding.artistName.setText(song.getArtist());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mediaPlayer != null) {
            outState.putBoolean("isPlaying", mediaPlayer.isPlaying());
            int index = musicViewModel.getSongs().indexOf(musicViewModel.getSelectedSong().getValue());
            outState.putInt("selectedSongIndex", index);
            outState.putInt("currentPosition", mediaPlayer.getCurrentPosition());
            Log.i(LOG_TAG, "Saving state: isPlaying="+ mediaPlayer.isPlaying() +", selectedSongIndex="+ musicViewModel.getSongs().indexOf(musicViewModel.currentSong) +", currentPosition="+ mediaPlayer.getCurrentPosition());
        }
    }
}