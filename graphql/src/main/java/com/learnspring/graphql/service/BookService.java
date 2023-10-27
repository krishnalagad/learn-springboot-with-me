package com.learnspring.graphql.service;

import com.learnspring.graphql.entity.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book updateBook(Book book, Integer bookId);
    Book getBook(Integer id);
    List<Book> getBooks();
}