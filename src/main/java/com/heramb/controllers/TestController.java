package com.heramb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heramb.models.Question;
import com.heramb.services.TestService;

@RestController
@RequestMapping("/services")
public class TestController {

	@Autowired
	TestService testService;
	
	@RequestMapping(value = "/{topic}/{test}/getQuestions", method = RequestMethod.GET, produces = "application/json")
	public List<Question> getQuestions(@PathVariable String topic,@PathVariable String test) {
		return testService.getQuestions(topic, test);
	}
}
