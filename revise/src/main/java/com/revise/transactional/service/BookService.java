package com.revise.transactional.service;

import com.revise.transactional.entity.Book;
import com.revise.transactional.repo.BookRepository;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class BookService implements InitializingBean, DisposableBean {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() { return bookRepository.findAll(); }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Transactional
    public Book createBook(Book book) { return bookRepository.save(book); }

    @Transactional
    public Book updateBook(Long id, @org.jetbrains.annotations.NotNull @NotNull Book details) {
        Book book = getBookById(id);
        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setGenre(details.getGenre());
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) { bookRepository.deleteById(id); }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean created.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean is going to destroy.");
    }
}