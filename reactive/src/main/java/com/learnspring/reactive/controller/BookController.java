package com.learnspring.reactive.controller;

import com.learnspring.reactive.entity.Book;
import com.learnspring.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
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
        return this.bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBook(@PathVariable Integer id) {
        return this.bookService.getBook(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.bookService.deleteBook(id);
    }
}