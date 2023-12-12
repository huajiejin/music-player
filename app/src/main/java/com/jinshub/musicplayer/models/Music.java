package com.jinshub.musicplayer.models;

// data class for Music, which is used to store the information of a music file
// including the title, artist, album, duration, path, and album art
public class Music {
	private String title;
	private String artist;
	private String album;
	private String duration;
	private String path;
	private String albumArt;

	public Music(String title, String artist, String album, String duration, String path, String albumArt) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.duration = duration;
		this.path = path;
		this.albumArt = albumArt;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() { return album; }

	public String getDuration() {
		return duration;
	}

	public String getPath() {
		return path;
	}

	public String getAlbumArt() {
		return albumArt;
	}
}
