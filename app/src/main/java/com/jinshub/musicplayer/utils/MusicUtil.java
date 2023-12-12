package com.jinshub.musicplayer.utils;

import com.jinshub.musicplayer.R;
import com.jinshub.musicplayer.models.Music;

import java.util.ArrayList;
import java.util.List;

// the util class for creating defalut music list
public class MusicUtil {
	public static List<Music> getDefaultMusicList() {
		List<Music> musicList = new ArrayList<>();

		musicList.add(new Music("The Cradle of Your Soul", "lemonmusicstudio", "", "2:57", R.raw.the_cradle_of_your_soul, R.drawable.lemonmusicstudio));
		musicList.add(new Music("Leva - Eternity", "lemonmusicstudio", "", "2:24", R.raw.leva_eternity, R.drawable.lemonmusicstudio));
		musicList.add(new Music("Weeknds", "DayFox", "", "3:28", R.raw.weeknds, R.drawable.dayfox));

		return musicList;
	}
}