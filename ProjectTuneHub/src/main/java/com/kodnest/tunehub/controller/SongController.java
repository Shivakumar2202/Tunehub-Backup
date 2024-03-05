package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {
	@Autowired
	SongServiceimpl songService;
	@Autowired
	UserServiceimpl userService;
	
	@PostMapping("/addsong")
	public String addsongs(@ModelAttribute Song song) {
		
		String nsong=song.getName();
		boolean status=songService.songExist(nsong);
		if(status==false) {
		songService.addsongs(song);
		System.out.println("Song added successfully..");
		}
		else {
			System.out.println("Song already exist!....");
		}
		return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songlist=songService.fetchAllSongs();
		model.addAttribute("songs", songlist);
		return "displaysongs";
	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model,HttpSession session) {
		String mail = (String) session.getAttribute("email");
		User user=userService.getUser(mail);
		boolean premium=user.isIspremium();
		if(premium) {
		List<Song> songlist=songService.fetchAllSongs();
		model.addAttribute("songs", songlist);
		return "displaysongs";
		}
		else {
			return "pay";
		}
	}
	

}
