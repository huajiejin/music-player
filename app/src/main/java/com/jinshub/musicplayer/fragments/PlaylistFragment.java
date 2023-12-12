package com.jinshub.musicplayer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinshub.musicplayer.R;
import com.jinshub.musicplayer.adapters.PlaylistAdapter;
import com.jinshub.musicplayer.databinding.FragmentPlaylistBinding;
import com.jinshub.musicplayer.models.Music;
import com.jinshub.musicplayer.utils.MusicUtil;
import com.jinshub.musicplayer.viewmodels.MusicViewModel;

import java.util.List;

public class PlaylistFragment extends Fragment {
    private FragmentPlaylistBinding binding;
    private PlaylistAdapter adapter;
    private MusicViewModel musicViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false);

        binding.recyclerLauncherView.setEdgeItemsCenteringEnabled(true);

        binding.recyclerLauncherView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerLauncherView.setLayoutManager(linearLayoutManager);

        musicViewModel = new ViewModelProvider(requireActivity()).get(MusicViewModel.class);
        adapter = new PlaylistAdapter(musicViewModel);
        binding.recyclerLauncherView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}