package com.jinshub.musicplayer.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jinshub.musicplayer.databinding.PlaylistItemBinding;
import com.jinshub.musicplayer.models.Music;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {
    private List<Music> songs;
    private LayoutInflater layoutInflater;
    // Tag for logging messages
    private static final String LOG_TAG = "PlaylistAdapter";

    public PlaylistAdapter(List<Music> songs) {
        this.songs = songs;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PlaylistItemBinding binding = PlaylistItemBinding.inflate(layoutInflater, parent, false);
        return new PlaylistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        Log.i(LOG_TAG, "onBindViewHolder("+ position +")");
        Music song = songs.get(position);
        holder.binding.songTitle.setText(song.getTitle());
        holder.binding.albumArt.setImageResource(song.getAlbumArtResourceId());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        public PlaylistItemBinding binding;

        public PlaylistViewHolder(PlaylistItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}