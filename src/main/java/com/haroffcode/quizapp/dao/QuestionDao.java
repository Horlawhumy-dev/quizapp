package com.haroffcode.quizapp.dao;

import com.haroffcode.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<Question, Integer> {
}
