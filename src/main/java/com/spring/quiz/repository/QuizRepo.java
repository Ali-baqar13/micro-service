package com.spring.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.quiz.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

     
    
}
