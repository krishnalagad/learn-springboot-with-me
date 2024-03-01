package com.learnspring.quiz_api.repos;

import com.learnspring.quiz_api.domain.Question;
import com.learnspring.quiz_api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findFirstByQuiz(Quiz quiz);

    boolean existsByQuestionTitleIgnoreCase(String questionTitle);

    boolean existsByOption1IgnoreCase(String option1);

    boolean existsByOption2IgnoreCase(String option2);

    boolean existsByOption3IgnoreCase(String option3);

    boolean existsByOption4IgnoreCase(String option4);

    boolean existsByCorrectOptionIgnoreCase(String correctOption);

}
