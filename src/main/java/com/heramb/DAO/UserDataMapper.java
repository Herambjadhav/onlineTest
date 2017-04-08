package com.heramb.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.heramb.models.UserData;

public class UserDataMapper implements RowMapper<UserData>{

	public UserData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		UserData userData = new UserData();
		userData.setId(resultSet.getInt("id"));
		userData.setTopic(resultSet.getString("topic"));
		userData.setTest(resultSet.getString("test"));
		userData.setScore(resultSet.getInt("score"));
		userData.setEmailId(resultSet.getString("emailId"));
		userData.setTimestamp(resultSet.getString("timestamp"));
		return userData;
	}
	
}
