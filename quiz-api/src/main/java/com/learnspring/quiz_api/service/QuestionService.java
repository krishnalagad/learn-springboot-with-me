package com.learnspring.quiz_api.service;

import com.learnspring.quiz_api.entity.Question;
import com.learnspring.quiz_api.entity.Quiz;
import com.learnspring.quiz_api.model.QuestionDTO;
import com.learnspring.quiz_api.repos.QuestionRepository;
import com.learnspring.quiz_api.repos.QuizRepository;
import com.learnspring.quiz_api.util.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    private final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public QuestionService(final QuestionRepository questionRepository,
            final QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public List<QuestionDTO> findAll() {
        final List<Question> questions = questionRepository.findAll(Sort.by("id"));
        return questions.stream()
                .map(question -> mapToDTO(question, new QuestionDTO()))
                .toList();
    }

    public QuestionDTO get(final Long id) {
        return questionRepository.findById(id)
                .map(question -> mapToDTO(question, new QuestionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public QuestionDTO create(final QuestionDTO questionDTO) {
        final Question question = new Question();
        Question finalQuestion = mapToEntity(questionDTO, question);
        Question savedQuestion = questionRepository.save(finalQuestion);
        this.logger.info("{}", savedQuestion);
        return this.mapToDTO(savedQuestion, new QuestionDTO());
    }

    public void update(final Long id, final QuestionDTO questionDTO) {
        final Question question = questionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(questionDTO, question);
        questionRepository.save(question);
    }

    public void delete(final Long id) {
        questionRepository.deleteById(id);
    }

    public List<QuestionDTO> getQuestionsByQuiz(Long id) {
        Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new NotFoundException("Quiz doesn't exists !!"));
        List<QuestionDTO> questions = new java.util.ArrayList<>(this.questionRepository.findQuestionsByQuiz(quiz)
                .stream()
                .map(obj -> mapToDTO(obj, new QuestionDTO())).toList());

        Collections.shuffle(questions);
        return questions;
    }

    private QuestionDTO mapToDTO(final Question question, final QuestionDTO questionDTO) {
        questionDTO.setId(question.getId());
        questionDTO.setQuestionTitle(question.getQuestionTitle());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        questionDTO.setCorrectOption(question.getCorrectOption());
        questionDTO.setQuiz(question.getQuiz() == null ? null : question.getQuiz().getId());
        return questionDTO;
    }

    private Question mapToEntity(final QuestionDTO questionDTO, final Question question) {
        question.setQuestionTitle(questionDTO.getQuestionTitle());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        question.setCorrectOption(questionDTO.getCorrectOption());
        final Quiz quiz = questionDTO.getQuiz() == null ? null : quizRepository.findById(questionDTO.getQuiz())
                .orElseThrow(() -> new NotFoundException("quiz not found"));
        question.setQuiz(quiz);
        return question;
    }

    public boolean questionTitleExists(final String questionTitle) {
        return questionRepository.existsByQuestionTitleIgnoreCase(questionTitle);
    }

    public boolean option1Exists(final String option1) {
        return questionRepository.existsByOption1IgnoreCase(option1);
    }

    public boolean option2Exists(final String option2) {
        return questionRepository.existsByOption2IgnoreCase(option2);
    }

    public boolean option3Exists(final String option3) {
        return questionRepository.existsByOption3IgnoreCase(option3);
    }

    public boolean option4Exists(final String option4) {
        return questionRepository.existsByOption4IgnoreCase(option4);
    }

    public boolean correctOptionExists(final String correctOption) {
        return questionRepository.existsByCorrectOptionIgnoreCase(correctOption);
    }

}