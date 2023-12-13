package com.jinshub.musicplayer.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jinshub.musicplayer.models.Music;
import com.jinshub.musicplayer.utils.MusicUtil;

import java.util.List;

// View model for the music player fragment
public class MusicViewModel extends ViewModel {
    private List<Music> songs = MusicUtil.getDefaultMusicList();
    private final MutableLiveData<Music> selectedSong = new MutableLiveData<>();
    public Music currentSong = MusicUtil.getDefaultMusicList().get(0);
    // Tag for logging messages
    private static final String LOG_TAG = "MusicViewModel";

    public List<Music> getSongs() {
        return songs;
    }

    public void selectSong(Music song) {
        if (song == currentSong) {
            return;
        }
        selectedSong.setValue(song);
        currentSong = song;
    }

    public LiveData<Music> getSelectedSong() {
        return selectedSong;
    }

    // select the previous song in the playlist, or the last song if the current song is the first song
    public void selectPrevSong() {
        int index = getIndexOfSong(currentSong);
        if (index > 0) {
            selectedSong.setValue(songs.get(index - 1));
        } else {
            selectedSong.setValue(songs.get(songs.size() - 1));
        }
    }
    
    // select the next song in the playlist, or the first song if the current song is the last song
    public void selectNextSong() {
        int index = getIndexOfSong(currentSong);
        Log.i(LOG_TAG, "selectNextSong " + index);
        if (index < songs.size() - 1) {
            selectedSong.setValue(songs.get(index + 1));
        } else {
            selectedSong.setValue(songs.get(0));
        }
    }

    public Integer getIndexOfSong(Music song) {
        if (song == null) {
            return -1;
        }
        // compare their titles
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(song.getTitle())) {
                return i;
            }
        }
        return -1;
    }
}
