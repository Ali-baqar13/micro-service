package com.spring.quiz;

import com.spring.quiz.model.Question;
import com.spring.quiz.model.Quiz;
import com.spring.quiz.model.Response;
import com.spring.quiz.repository.QuizRepo;
import com.spring.quiz.service.QuizServices;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuizServicesTests {
    private final QuizServices service = new QuizServices();

    QuizServicesTests() {
        service.quizDao = mock(QuizRepo.class);
        Question first = new Question();
        first.setId(10);
        first.setRightAnswer("A");
        Question second = new Question();
        second.setId(20);
        second.setRightAnswer("B");
        Quiz quiz = new Quiz();
        quiz.setQuestions(List.of(first, second));
        when(service.quizDao.findById(1)).thenReturn(Optional.of(quiz));
        when(service.quizDao.findById(99)).thenReturn(Optional.empty());
    }

    @Test
    void scoresByQuestionIdAndCountsOnlyCorrectAnswers() {
        assertEquals(1, service.getScore(1,
                List.of(new Response(20, "B"), new Response(10, "wrong"))).getBody());
        assertEquals(2, service.getScore(1,
                List.of(new Response(20, "B"), new Response(10, "A"))).getBody());
        assertEquals(0, service.getScore(1, List.of()).getBody());
    }

    @Test
    void rejectsDuplicateAndUnrelatedAnswers() {
        assertEquals(400, service.getScore(1,
                List.of(new Response(10, "A"), new Response(10, "A"))).getStatusCode().value());
        assertEquals(400, service.getScore(1,
                List.of(new Response(30, "A"))).getStatusCode().value());
        assertEquals(400, service.getScore(1,
                List.of(new Response(10, null))).getStatusCode().value());
    }

    @Test
    void missingQuizReturnsNotFound() {
        assertEquals(404, service.getScore(99, List.of()).getStatusCode().value());
        assertEquals(404, service.getQuizQuestions(99).getStatusCode().value());
    }
}
