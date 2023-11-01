package com.learnspring.reactive.service;

import com.learnspring.reactive.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Mono<Book> createBook(Book book);
    Mono<Book> updateBook(Book book, Integer bookId);
    Mono<Book> getBook(Integer bookId);
    Flux<Book> getBooks();
    Mono<Void> deleteBook(Integer bookId);
    Flux<Book> searchBook(String query);
}