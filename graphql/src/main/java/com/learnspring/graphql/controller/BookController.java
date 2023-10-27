package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = this.bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Integer id) {
        Book updateBook = this.bookService.updateBook(book, id);
        return ResponseEntity.ok(updateBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        Book book = this.bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.bookService.getBooks();
        return ResponseEntity.ok(books);
    }
}