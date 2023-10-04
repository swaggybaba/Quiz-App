package com.prafful.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prafful.demo.Question;
import com.prafful.demo.dao.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	public List<Question> getAllQuestions() {
		
		return questionDao.findAll();
	}
	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}
	public String addQuestion(Question question) {
		questionDao.save(question);
		return "success";
	}

}
