package com.spring.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.quiz.model.Question;
import com.spring.quiz.model.QuestionWrapper;
import com.spring.quiz.model.Response;
import com.spring.quiz.service.QuizServices;

@RequestMapping("quiz")
@RestController
public class QuizController {
    @Autowired

    public QuizServices quizService;
    

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam int numQ, @RequestParam String category, @RequestParam String title) {


        return quizService.creatQuiz(title, numQ, category);
    }

    @GetMapping("get-quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuizQuestions(id);

    }

    @GetMapping("validate/{id}")
    public ResponseEntity<Integer> validateCount(@PathVariable int id, @RequestParam List<Response> response) {
        return quizService.getScore(id, response);
    }
    
    
}
