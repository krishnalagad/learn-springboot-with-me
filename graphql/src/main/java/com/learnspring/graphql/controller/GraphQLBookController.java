package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLBookController {

    @Autowired
    private BookService bookService;

    @QueryMapping("allBooks")
    public List<Book> getAll() {
        return this.bookService.getBooks();
    }

    @QueryMapping("getBook")
    public Book getBook(@Argument Integer bookId) {
        return this.bookService.getBook(bookId);
    }
}