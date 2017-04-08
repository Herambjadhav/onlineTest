package com.heramb.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.heramb.models.UserAnswer;
import com.heramb.models.UserData;

@Component
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getUser(String emailId) {
		String query = "Select * from users where emailId = '" + emailId + "' ;";
		int record = 0;
		try {
			record = jdbcTemplate.queryForList(query).size();
			System.out.println("Record found for user : " + emailId);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return record;
	}

	public List<UserData> getUserData(String emailId) {
		String query = "Select * from user_data where emailId = '" + emailId + "' ;";
		List<UserData> userData = null;
		try {
			userData = jdbcTemplate.query(query, new UserDataMapper());
			System.out.println("User Data record found for user : " + userData.size());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userData;
	}

	public void createUserRecord(String emailId, String name) {
		String query = "insert into users values (?, ?)";
		try {
			jdbcTemplate.update(query, name, emailId);
			System.out.println("New record created for user : " + emailId);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public int createUserDataRecord(UserData userData) {
		String query = "insert into user_data values (?, ?, ?, ?, ?, ?)";
		Integer id = -1;
		try {
			jdbcTemplate.update(query, null, userData.getTopic(), userData.getTest(), userData.getScore(),
					userData.getEmailId(), userData.getTimestamp());
			// id = (Integer) jdbcTemplate.queryForObject("select
			// sqlite3_last_insert_rowid();", Integer.class);
			id = (Integer) jdbcTemplate.queryForObject(
					"SELECT MAX(id) from user_data where emailId = '" + userData.getEmailId() + "';", Integer.class);
			System.out.println("New User Data record created. Id : " + id);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return id;
	}

	public void saveUserAnswers(UserData userData) {
		for (UserAnswer userAnswer: userData.getUserAnswers()) {
			String query = "insert into user_answers values (?, ?, ?)";
			try {
				jdbcTemplate.update(query, userData.getId(), userAnswer.getQuestion_id(), userAnswer.getOption());
				System.out.println("Answers saved for user : " + userData.getEmailId());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
	public List<UserAnswer> getUserAnswers(int id) {
		String query = "Select * from user_answers where id = '"+id+"';";
		List<UserAnswer> userAnswers = null;
		try {
			userAnswers = jdbcTemplate.query(query, new UserAnswerMapper());
			System.out.println("Fetchning user answers for :  " + id);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userAnswers;
	}

}
