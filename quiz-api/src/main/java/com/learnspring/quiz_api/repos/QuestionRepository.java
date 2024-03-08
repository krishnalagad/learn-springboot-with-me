package com.learnspring.quiz_api.repos;

import com.learnspring.quiz_api.entity.Question;
import com.learnspring.quiz_api.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findFirstByQuiz(Quiz quiz);

    boolean existsByQuestionTitleIgnoreCase(String questionTitle);

    boolean existsByOption1IgnoreCase(String option1);

    boolean existsByOption2IgnoreCase(String option2);

    boolean existsByOption3IgnoreCase(String option3);

    boolean existsByOption4IgnoreCase(String option4);

    boolean existsByCorrectOptionIgnoreCase(String correctOption);

    Set<Question> findQuestionsByQuiz(Quiz quiz);

}