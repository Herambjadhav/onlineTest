package com.heramb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heramb.models.UserData;
import com.heramb.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/getUser/{emailId}/{name}", method = RequestMethod.GET)
	public String getQuestions(@PathVariable String emailId,@PathVariable String name ) {
		return userService.getUser(emailId, name);
	}
	
	@RequestMapping(value = "/saveUserData", method = RequestMethod.POST, produces = "application/json")
	public String saveUserData(@RequestBody UserData userData ) {
		userService.saveUserData(userData);
		return "success";
		
	}
	
	@RequestMapping(value = "/{emailId}/reviewData", method = RequestMethod.GET, produces = "application/json")
	public List<UserData> reviewData(@PathVariable String emailId ) {
		return userService.reviewData(emailId);
	}
	
}
