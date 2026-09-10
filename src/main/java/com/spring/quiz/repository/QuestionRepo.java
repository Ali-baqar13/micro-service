package com.spring.quiz.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.quiz.model.Question;



public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);
   @Query(
    value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",
    nativeQuery = true
)
List<Question> findRandomQuestionsByCategory(
    @Param("category") String category,
    @Param("numQ") int numQ
);  
    
}
