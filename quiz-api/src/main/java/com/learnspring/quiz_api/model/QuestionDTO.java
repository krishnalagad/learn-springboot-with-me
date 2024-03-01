package com.learnspring.quiz_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionDTO {

    private Long id;

    @NotNull
    @Size(max = 5000)
    @QuestionQuestionTitleUnique
    private String questionTitle;

    @NotNull
    @Size(max = 255)
    @QuestionOption1Unique
    private String option1;

    @NotNull
    @Size(max = 255)
    @QuestionOption2Unique
    private String option2;

    @Size(max = 255)
    @QuestionOption3Unique
    private String option3;

    @Size(max = 255)
    @QuestionOption4Unique
    private String option4;

    @NotNull
    @Size(max = 255)
    @QuestionCorrectOptionUnique
    private String correctOption;

    private Long quiz;

}
