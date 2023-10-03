package com.prafful.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prafful.demo.Question;
import com.prafful.demo.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allquestions")
	public List<Question> getAllQuestions() {
		List<Question> questions = questionService.getAllQuestions();
		System.out.println(questions.size()+ "In controller ");
		for(Question question :questions) {
			System.out.println(question.toString());
		}
		return questions;
	}
}
