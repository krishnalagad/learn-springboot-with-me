package com.revise.transactional.service;

import com.revise.transactional.entity.Book;
import com.revise.transactional.repo.BookRepository;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Service
public class BookService implements InitializingBean, DisposableBean {
    @Autowired
    private BookRepository bookRepository;
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Transactional
    public Book createBook(Book book) { return bookRepository.save(book); }

    @Transactional
    @CachePut(value = "books", key = "#id")
    public Book updateBook(Long id, @org.jetbrains.annotations.NotNull @NotNull Book details) {
        Book book = getBookById(id);
        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setGenre(details.getGenre());
        return bookRepository.save(book);
    }

    @Transactional
    @CacheEvict(value = "books", key = "#id", allEntries = true, beforeInvocation = true)
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