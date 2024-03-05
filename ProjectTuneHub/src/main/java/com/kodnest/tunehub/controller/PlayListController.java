package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlayListServiceimpl;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;


@Controller
public class PlayListController {
	@Autowired
	SongServiceimpl songService;
	
	@Autowired
	PlayListServiceimpl playListService;
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {
		List<Song> songlist=songService.fetchAllSongs();
		model.addAttribute("songs", songlist);
		return "createplaylists";
	}
	
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		playListService.addplaylist(playlist);
		
		List<Song> songList = playlist.getSongs();
		for(Song s:songList) {
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
		}
		return "adminhome";
	}
	
	@GetMapping("/viewplaylist")
	public String viewplaylist(Model model) {
		List<Playlist> playlistsong=playListService.fetchAllPlaylist();
		model.addAttribute("playlists", playlistsong);
		return "displayplaylist";
	}
	
	
	
	
	

}
