package com.learnspring.reactive.controller;

import com.learnspring.reactive.entity.Book;
import com.learnspring.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Mono<Book> create(@RequestBody Book book) {
        return this.bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Mono<Book> update(@RequestBody Book book, @PathVariable Integer id) {
        return this.bookService.updateBook(book, id);
    }

    @GetMapping("/")
    public Flux<Book> getBooks() {
        return this.bookService.getBooks().delayElements(Duration.ofSeconds(1)).log().map(book -> {
            book.setDescription(book.getDescription().toUpperCase());
            return book;
        });
    }

    @GetMapping("/{id}")
    public Mono<Book> getBook(@PathVariable Integer id) {
        return this.bookService.getBook(id).map(book -> {
            book.setName(book.getName().toUpperCase());
            return book;
        });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.bookService.deleteBook(id);
    }
}