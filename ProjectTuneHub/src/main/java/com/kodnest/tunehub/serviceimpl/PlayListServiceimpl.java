package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.repository.PlayListRepository;
import com.kodnest.tunehub.service.PlayListService;

@Service
public class PlayListServiceimpl implements PlayListService{
	
	@Autowired
	PlayListRepository playListRepository;

	@Override
	public void addplaylist(Playlist playlist) {
		playListRepository.save(playlist);
	}

	public List<Playlist> fetchAllPlaylist() {
		List<Playlist> playlists=playListRepository.findAll();
		return playlists;
	}

}
