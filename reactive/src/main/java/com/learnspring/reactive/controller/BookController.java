package com.learnspring.reactive.controller;

import com.learnspring.reactive.entity.Book;
import com.learnspring.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping("/search")
    public Flux<Book> searchBook(@RequestParam("query") String query) {
        query = "%" + query + "%";
        Flux<Book> bookFlux = this.bookService.searchBook(query);
        return bookFlux;
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.bookService.deleteBook(id);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /** @noinspection deprecation*/
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> test1() {
        return Flux.just(1,2,3,4,5)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}