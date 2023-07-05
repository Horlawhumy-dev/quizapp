package com.haroffcode.quizapp.controller;

import com.haroffcode.quizapp.model.Question;
import com.haroffcode.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @PostMapping("")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
        return questionService.updateQuestionById(question, id);
    }
}
