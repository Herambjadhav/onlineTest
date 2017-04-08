package com.heramb.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heramb.DAO.UserDAO;
import com.heramb.models.Question;
import com.heramb.models.UserAnswer;
import com.heramb.models.UserData;

@Component
public class UserService {
	
	@Autowired
	UserDAO userDAO;	
	
	@Autowired
	TestService testService;

	public String getUser(String emailId, String name){
		// Check if User already exist
		if(userDAO.getUser(emailId) == 0){
			//Create User
			userDAO.createUserRecord(emailId, name);
			return "NEW";
		} else{
			//Fetch user test records
			if(userDAO.getUserData(emailId).size() == 0)
				return "NEW";
			else
				return "EXISTING";
		}
	}
	
	public void saveUserData(UserData userData){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		userData.setTimestamp(timestamp.toString());
		
		// Save userData
		userData.setId(userDAO.createUserDataRecord(userData));
		
		// Save user_answers
		userDAO.saveUserAnswers(userData);
	}

	public List<UserData> reviewData(String emailId) {
		List<UserData> userDataList = userDAO.getUserData(emailId);
		for(UserData userData : userDataList){
			userData.setUserAnswers(userDAO.getUserAnswers(userData.getId()));
			userData.setQuestionSet(testService.getQuestions(userData.getTopic(), userData.getTest()));
			for(UserAnswer userAnswer : userData.getUserAnswers()){
				for(Question question : userData.getQuestionSet()){
					if(userAnswer.getQuestion_id() == question.getQuestionId()){
						question.setSelectedOption(userAnswer.getOption());
					}
				}
			}
		}
		
		return userDataList;
	}
}
