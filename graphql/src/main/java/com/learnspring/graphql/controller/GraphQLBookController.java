package com.learnspring.graphql.controller;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.service.BookService;
import lombok.*;
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
    public Book create(@Argument BookInput book) {
        Book bookObj = new Book(0, book.getTitle(), book.getDesc(), book.getAuthor(),
                book.getPrice(), book.getPages());
        return this.bookService.createBook(bookObj);
    }

    @MutationMapping("updateBook")
    public Book update(@Argument BookInput book, @Argument Integer bookId) {
        Book obj = new Book(0, book.getTitle(), book.getDesc(), book.getAuthor(),
                book.getPrice(), book.getPages());
        return this.bookService.updateBook(obj, bookId);
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
@ToString
class BookInput {
    private String title;
    private String desc;
    private String author;
    private double price;
    private int pages;
}