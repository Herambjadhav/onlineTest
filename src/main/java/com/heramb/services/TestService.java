package com.heramb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heramb.DAO.QuestionDAO;
import com.heramb.models.Question;

@Component
public class TestService {
	
	@Autowired
	QuestionDAO questionDAO;	

	public List<Question> getQuestions(String topic, String test){
		return questionDAO.get(topic, test);
	}
}
