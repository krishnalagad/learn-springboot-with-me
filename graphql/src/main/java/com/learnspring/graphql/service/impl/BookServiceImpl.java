package com.learnspring.graphql.service.impl;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.repository.BookRepository;
import com.learnspring.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, Integer bookId) {
        Book obj = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException(String.format("Book not found with Id: %d", bookId)));
        obj.setTitle(book.getTitle());
        obj.setDesc(book.getDesc());
        obj.setPrice(book.getPrice());
        obj.setAuthor(book.getAuthor());
        obj.setPages(book.getPages());
        return this.bookRepository.save(obj);
    }

    @Override
    public Book getBook(Integer id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Book not found with id %d", id)));
    }

    @Override
    public List<Book> getBooks() {
        return  this.bookRepository.findAll();
    }
}