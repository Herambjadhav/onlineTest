package com.heramb.models;

import java.util.List;

public class Question {
	int questionId;
	String question;
	int correctOption;
	int selectedOption;
	
	public int getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
	List<String> options;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	
}
