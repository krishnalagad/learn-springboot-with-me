package com.learnspring.quiz_api.rest;

import com.learnspring.quiz_api.model.QuizDTO;
import com.learnspring.quiz_api.service.QuizService;
import com.learnspring.quiz_api.util.ReferencedException;
import com.learnspring.quiz_api.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuizResource {

    private final QuizService quizService;

    public QuizResource(final QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(quizService.get(id));
    }

    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody @Valid final QuizDTO quizDTO) {
        final QuizDTO createdId = quizService.create(quizDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateQuiz(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final QuizDTO quizDTO) {
        quizService.update(id, quizDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = quizService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}