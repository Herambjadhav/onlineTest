package com.heramb.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.heramb.models.UserAnswer;

public class UserAnswerMapper implements RowMapper<UserAnswer>{

	public UserAnswer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setId(resultSet.getInt("id"));
		userAnswer.setOption(resultSet.getInt("option"));
		userAnswer.setQuestion_id(resultSet.getInt("question"));
		return userAnswer;
	}
	
}
