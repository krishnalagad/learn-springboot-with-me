package com.learnspring.reactive.service.impl;

import com.learnspring.reactive.entity.Book;
import com.learnspring.reactive.repository.BookRepository;
import com.learnspring.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> createBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Mono<Book> updateBook(Book book, Integer bookId) {
        Mono<Book> oldBook = this.bookRepository.findById(bookId);
        Mono<Book> bookMono = oldBook.flatMap(obj -> {
            obj.setName(book.getName());
            obj.setDescription(book.getDescription());
            obj.setPublisher(book.getPublisher());
            obj.setAuthor(book.getAuthor());
            return this.bookRepository.save(obj);
        });
        return bookMono;
    }

    @Override
    public Mono<Book> getBook(Integer bookId) {
        return this.bookRepository.findById(bookId);
    }

    @Override
    public Flux<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteBook(Integer bookId) {
        Mono<Void> voidMono = this.bookRepository.findById(bookId).flatMap(book -> this.bookRepository.delete(book));
        return voidMono;
    }

    @Override
    public Flux<Book> searchBook(String query) {
        Flux<Book> bookFlux = this.bookRepository.searchBookByTitle(query);
        return bookFlux;
    }
}