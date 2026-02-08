package com.revise.transactional.service;

import com.revise.transactional.entity.BookLog;
import com.revise.transactional.repo.BookLogRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookLogService {

    private final BookLogRepository bookLogRepository;

    public BookLogService(BookLogRepository bookLogRepository) {
        this.bookLogRepository = bookLogRepository;
    }

    // Create
    public BookLog createBookLog(BookLog bookLog) {
        return bookLogRepository.save(bookLog);
    }

    // Read by ID
    @PostAuthorize("returnObject.performedBy == authentication.name")
    public Optional<BookLog> getBookLogById(Long id) {
        return bookLogRepository.findById(id);
    }

    // Read all
    public List<BookLog> getAllBookLogs() {
        return bookLogRepository.findAll();
    }

    // Update
    public BookLog updateBookLog(Long id, BookLog bookLogDetails) {
        BookLog bookLog = bookLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookLog not found with id: " + id));

        bookLog.setAction(bookLogDetails.getAction());
        bookLog.setBookName(bookLogDetails.getBookName());
        bookLog.setPerformedBy(bookLogDetails.getPerformedBy());
        bookLog.setTimeStamp(bookLogDetails.getTimeStamp());

        return bookLogRepository.save(bookLog);
    }

    // Delete
    public void deleteBookLog(Long id) {
        BookLog bookLog = bookLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookLog not found with id: " + id));
        bookLogRepository.delete(bookLog);
    }
}