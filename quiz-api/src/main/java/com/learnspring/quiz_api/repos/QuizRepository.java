package com.learnspring.quiz_api.repos;

import com.learnspring.quiz_api.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
}