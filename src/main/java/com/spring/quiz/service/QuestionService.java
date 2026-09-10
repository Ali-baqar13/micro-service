package com.spring.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.quiz.model.Question;
import com.spring.quiz.repository.QuestionRepo;

/**
 * QuestionService
 */
@Service
public class QuestionService {

    private final QuestionRepo questionRepository;

    public QuestionService(QuestionRepo questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
    }
}