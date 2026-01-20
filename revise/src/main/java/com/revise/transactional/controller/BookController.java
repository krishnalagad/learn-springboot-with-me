package com.revise.transactional.controller;

import com.revise.transactional.entity.Book;
import com.revise.transactional.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() { return bookService.getAllBooks(); }

    @PostMapping
    public Book create(@RequestBody Book book) { return bookService.createBook(book); }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { bookService.deleteBook(id); }
}