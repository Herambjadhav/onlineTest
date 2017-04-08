package com.heramb.DAO;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.heramb.models.Question;

@Component
public class QuestionDAO {

	public List<Question> get(String topic, String test) {
		List<Question> questionSet = null;
		ObjectMapper mapper = new ObjectMapper();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(
				classLoader.getResource("QuestionData/" + topic + "/" + test + "/question_set.json").getFile());

		try {
			questionSet = mapper.readValue(file,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Question.class));

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return questionSet;
	}
}
