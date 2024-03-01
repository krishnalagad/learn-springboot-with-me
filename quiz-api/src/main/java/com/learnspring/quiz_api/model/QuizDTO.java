package com.learnspring.quiz_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
public class QuizDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private Integer totalQuestions;

    @NotNull
    private Integer maxMarks;

    private Set<QuestionDTO> questions = new LinkedHashSet<>();

}