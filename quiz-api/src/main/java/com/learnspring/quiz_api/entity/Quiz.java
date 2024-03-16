package com.learnspring.quiz_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Quiz {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Integer maxMarks;

    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions;

}