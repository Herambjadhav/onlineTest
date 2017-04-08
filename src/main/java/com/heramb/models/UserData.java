package com.heramb.models;

import java.util.List;

public class UserData {

	int id;
	String topic;
	String test;
	float score;
	String emailId;
	String timestamp;
	List<UserAnswer> userAnswers;
	List<Question> questionSet;
	
	public List<Question> getQuestionSet() {
		return questionSet;
	}
	public void setQuestionSet(List<Question> questionSet) {
		this.questionSet = questionSet;
	}
	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}
	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
	
}
