package com.spring.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.quiz.model.Question;
import com.spring.quiz.model.QuestionWrapper;
import com.spring.quiz.model.Quiz;
import com.spring.quiz.model.Response;
import com.spring.quiz.repository.QuestionRepo;
import com.spring.quiz.repository.QuizRepo;

@Service
public class QuizServices {
    @Autowired
    public QuizRepo quizDao;
    @Autowired
    public QuestionRepo questionRepo;

    public ResponseEntity<String> creatQuiz(String title, int numQ, String category) {

        Quiz quiz = new Quiz();
        List<Question> randomQuestions = questionRepo.findRandomQuestionsByCategory(category, numQ);
        quiz.setQuizTitle(title);
        quiz.setQuestions(randomQuestions);
        quizDao.save(quiz);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        // there must be que=estion Wrapper
        List<Question> QuestionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> QuestionsForUser = new ArrayList<>();

        for (Question q : QuestionFromDb) {
            QuestionWrapper qWrapper = new QuestionWrapper(q.getId(), q.getTitle(), q.getOptions1(), q.getOptions2(),
                    q.getOptions3(), q.getOptions4());
            QuestionsForUser.add(qWrapper);
        }

        return new ResponseEntity<>(QuestionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(int id, List<Response> response) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> QuestionFromDb = quiz.getQuestions();
        int i = 0;
        int count = 1;
        for (Response r : response) {
            // Optional<Question> QuestionWithRightAnswer = questionRepo.findById(r.getId());
            
            r.getResponse().equals(QuestionFromDb.get(i).getRightAnswer());
            count++;
        }

        return new ResponseEntity<>(count, HttpStatus.OK);

    }

}
