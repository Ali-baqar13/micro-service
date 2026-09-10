package com.spring.quiz.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String title;
    private String rightAnswer;
    private String category;
    private String options1;
    private String options2;
    private String options3;
    private String options4;

    
}
