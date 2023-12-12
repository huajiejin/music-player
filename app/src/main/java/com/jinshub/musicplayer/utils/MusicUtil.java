package com.jinshub.musicplayer.utils;

import com.jinshub.musicplayer.models.Music;

import java.util.ArrayList;
import java.util.List;

// the util class for creating defalut music list
public class MusicUtil {
	public static List<Music> getDefaultMusicList() {
		List<Music> musicList = new ArrayList<>();

		musicList.add(new Music("The Cradle of Your Soul", "lemonmusicstudio", "", "2:57", "android.resource://com.jinshub.musicplayer/raw/the_cradle_of_your_soul.mp3", "android.resource://com.jinshub.musicplayer/raw/lemonmusicstudio.png"));
		musicList.add(new Music("Leva - Eternity", "lemonmusicstudio", "", "2:24", "android.resource://com.jinshub.musicplayer/raw/leva_eternity.mp3", "android.resource://com.jinshub.musicplayer/raw/lemonmusicstudio.png"));
		musicList.add(new Music("Weeknds", "DayFox", "", "3:28", "android.resource://com.jinshub.musicplayer/raw/weeknds.mp3", "android.resource://com.jinshub.musicplayer/raw/dayfox.jpg"));

		return musicList;
	}
}