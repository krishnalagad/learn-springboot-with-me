package com.learnspring.quiz_api.service;

import com.learnspring.quiz_api.entity.Question;
import com.learnspring.quiz_api.entity.Quiz;
import com.learnspring.quiz_api.model.QuestionDTO;
import com.learnspring.quiz_api.model.QuizDTO;
import com.learnspring.quiz_api.repos.QuestionRepository;
import com.learnspring.quiz_api.repos.QuizRepository;
import com.learnspring.quiz_api.util.NotFoundException;
import com.learnspring.quiz_api.util.ReferencedWarning;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizService(final QuizRepository quizRepository,
                       final QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuizDTO> findAll() {
        final List<Quiz> quizzes = quizRepository.findAll(Sort.by("id"));
        return quizzes.stream()
                .map(quiz -> mapToDTO(quiz, new QuizDTO()))
                .toList();
    }

    public QuizDTO get(final Long id) {
        return quizRepository.findById(id)
                .map(quiz -> mapToDTO(quiz, new QuizDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public QuizDTO create(final QuizDTO quizDTO) {
        final Quiz quiz = new Quiz();
        Quiz finalQuiz = mapToEntity(quizDTO, quiz);
        Quiz savedQuiz = this.quizRepository.save(finalQuiz);
        return this.mapToDTO(savedQuiz, new QuizDTO());
    }

    public void update(final Long id, final QuizDTO quizDTO) {
        final Quiz quiz = quizRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(quizDTO, quiz);
        quizRepository.save(quiz);
    }

    public void delete(final Long id) {
        quizRepository.deleteById(id);
    }

    private QuizDTO mapToDTO(final Quiz quiz, final QuizDTO quizDTO) {
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setTotalQuestions(quiz.getTotalQuestions());
        quizDTO.setMaxMarks(quiz.getMaxMarks());
        quizDTO.setQuestions(quiz.getQuestions().stream().map(ques -> mapToDTOQuestion(ques, new QuestionDTO()))
                .collect(Collectors.toSet()));
        return quizDTO;
    }

    private Quiz mapToEntity(final QuizDTO quizDTO, final Quiz quiz) {
        quiz.setTitle(quizDTO.getTitle());
        quiz.setTotalQuestions(quizDTO.getTotalQuestions());
        quiz.setMaxMarks(quizDTO.getMaxMarks());

        return quiz;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Quiz quiz = quizRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Question quizQuestion = questionRepository.findFirstByQuiz(quiz);
        if (quizQuestion != null) {
            referencedWarning.setKey("quiz.question.quiz.referenced");
            referencedWarning.addParam(quizQuestion.getId());
            return referencedWarning;
        }
        return null;
    }

    private QuestionDTO mapToDTOQuestion(final Question question, final QuestionDTO questionDTO) {
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
}