package com.learnspring.graphql.service;

import com.learnspring.graphql.entity.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);
    Book getBook(Integer id);
    List<Book> getBooks();
}