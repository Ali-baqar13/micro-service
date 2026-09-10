package com.spring.quiz.controller;

import org.springframework.web.bind.annotation.RestController;

import com.spring.quiz.model.Question;
import com.spring.quiz.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    public QuestionService questionService;

    @GetMapping("get")
    public ResponseEntity<List<Question>> getQuestions() {

        List<Question> questions = questionService.getQuestions();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCatgory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }
    

    @PostMapping("create-question")
    public ResponseEntity<String> postQuestions(@RequestBody Question question) {

        questionService.addQuestion(question);

        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }
    // @PutMapping("update-question/{id}")
    // public ResponseEntity<List<Question>> updateQuestion(@PathVariable int id) {
        
    //     return 

    // }

}
