package com.haroffcode.quizapp.service;

import com.haroffcode.quizapp.dao.QuestionDao;
import com.haroffcode.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            questions = questionDao.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            //return new ResponseEntity<>("Could not get all questions from db", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    public ResponseEntity<String> addQuestion(Question question) {

        if (!isQuestionExisting(question)) {
            try {
                questionDao.save(question);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>("Question with the this title already exist.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success! Question is added to the database.", HttpStatus.CREATED);
    }

    protected boolean isQuestionExisting(Question question) {
        List<Question> questions = new ArrayList<>();
        boolean isExisting = false;
        try {
            //questions = getAllQuestions().getBody();
            questions = questionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return isExisting;
        }

//        assert questions != null;
        for (Question qs: questions) {
            if (qs.getQuestionTitle().equals(question.getQuestionTitle())) {
                isExisting = true;
                break;
            }
        }

        return isExisting;
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            questionDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("failed to delete question by id " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<>("Successfully deleted Question of id " + id, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> updateQuestionById(Question questionFromBody, Integer id) {
        Optional<Question> question = Optional.empty();

        try {
            question = questionDao.findById(id);
        } catch (Exception e ) {
            e.printStackTrace();
        }

        question.get().setQuestionTitle(questionFromBody.getQuestionTitle());
        question.get().setCategory(questionFromBody.getCategory());
        question.get().setAnswer(questionFromBody.getAnswer());
        question.get().setDifficulty(questionFromBody.getDifficulty());
        question.get().setOptionA(questionFromBody.getOptionA());
        question.get().setOptionB(questionFromBody.getOptionB());

        Question qs = question.map(this::Question);
//        Question qs = questionDao.findById(id);
//        questionDao.save(questionFromBody);

        return new ResponseEntity<>("Updated Successfully", HttpStatus.ACCEPTED);


    }


}
