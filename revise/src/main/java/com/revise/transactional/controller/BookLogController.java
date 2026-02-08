package com.revise.transactional.controller;

import com.revise.transactional.entity.BookLog;
import com.revise.transactional.service.BookLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class BookLogController {

    private final BookLogService bookLogService;

    public BookLogController(BookLogService bookLogService) {
        this.bookLogService = bookLogService;
    }

    // Create
    @PostMapping
    public ResponseEntity<BookLog> createBookLog(@RequestBody BookLog bookLog, Principal principal) {
        bookLog.setPerformedBy(principal.getName());
        bookLog.setTimeStamp(LocalDateTime.now());
        BookLog createdBookLog = bookLogService.createBookLog(bookLog);
        return new ResponseEntity<>(createdBookLog, HttpStatus.CREATED);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookLog> getBookLogById(@PathVariable Long id) {
        return bookLogService.getBookLogById(id)
                .map(bookLog -> new ResponseEntity<>(bookLog, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<BookLog>> getAllBookLogs() {
        List<BookLog> bookLogs = bookLogService.getAllBookLogs();
        return new ResponseEntity<>(bookLogs, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BookLog> updateBookLog(@PathVariable Long id, @RequestBody BookLog bookLogDetails) {
        try {
            BookLog updatedBookLog = bookLogService.updateBookLog(id, bookLogDetails);
            return new ResponseEntity<>(updatedBookLog, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookLog(@PathVariable Long id) {
        try {
            bookLogService.deleteBookLog(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}