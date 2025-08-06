package com.example.WebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController // it returns only data to client from server
//@Controller // it looks to send data + layout so it looks for view from the return tag
public class HomeController {

	@RequestMapping("/")
	//@ResponseBody // this is required when controller is used instead pf rest controller in scenario of passinf just data
	public String greet() {
		return "welcome!";	
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about page";
	}
}

