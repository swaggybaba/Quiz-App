package com.prafful.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prafful.demo.model.QuestionWrapper;
import com.prafful.demo.dao.QuestionDao;
import com.prafful.demo.dao.QuizDao;
import com.prafful.demo.model.Question;
import com.prafful.demo.model.Quiz;
import com.prafful.demo.model.Response;

@Service
public class QuizService {

	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	QuizDao quizDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz =new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		List<QuestionWrapper> resultList = new ArrayList<>();
		for(Question question:questions) {
			resultList.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
		}
		return new ResponseEntity<>(resultList,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right=0,ind=0;
		for(Response response: responses) {
			if(questions.get(ind).getRightAnswer().equals(response.getResponse())) {
				right++;
			}
			ind++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}
