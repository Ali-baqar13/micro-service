package com.spring.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor

public class QuestionWrapper {

    private int id;
    private String title;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    
}
