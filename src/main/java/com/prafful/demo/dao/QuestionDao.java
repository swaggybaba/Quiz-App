package com.prafful.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prafful.demo.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

}
