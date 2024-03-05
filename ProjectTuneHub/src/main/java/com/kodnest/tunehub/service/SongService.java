package com.kodnest.tunehub.service;

import java.util.List;

import com.kodnest.tunehub.entity.Song;

public interface SongService {

	
	public String addsongs(Song song);
	public boolean songExist(String nsong);
	public List<Song> fetchAllSongs();
	public void updateSong(Song s);
}
