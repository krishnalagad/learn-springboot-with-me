package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLBookController {

    @Autowired
    private BookService bookService;

    @MutationMapping("createBook")
    public Book create(@Argument BookInput bookInput) {
//        Book book = new Book(0, bookInput.getTitle(), bookInput.getDesc(), bookInput.getAuthor(),
//                bookInput.getPrice(), bookInput.getPages());
        return this.bookService.createBook(book);
    }

    @QueryMapping("allBooks")
    public List<Book> getAll() {
        return this.bookService.getBooks();
    }

    @QueryMapping("getBook")
    public Book getBook(@Argument Integer bookId) {
        return this.bookService.getBook(bookId);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class BookInput {
    private String title;
    private String desc;
    private String author;
    private double price;
    private int pages;
}