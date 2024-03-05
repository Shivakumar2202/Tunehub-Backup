package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceimpl implements SongService {

	@Autowired
	SongRepository songRepository;

	@Override
	public String addsongs(Song song) {
		songRepository.save(song);
		return "Song added Successfully";
	}

	@Override
	public boolean songExist(String nsong) {
		if (songRepository.findByName(nsong) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs=songRepository.findAll();
		return songs;
	}

	@Override
	public void updateSong(Song s) {
		songRepository.save(s);
	}

}
