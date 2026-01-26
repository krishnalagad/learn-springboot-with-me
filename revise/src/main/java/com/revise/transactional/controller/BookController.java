package com.revise.transactional.controller;

import com.revise.transactional.entity.Book;
import com.revise.transactional.repo.UserDetailsRepository;
import com.revise.transactional.service.BookService;
import com.revise.transactional.service.CacheInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private CacheInspectionService cacheInspectionService;

    @GetMapping("/all")
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        ResponseEntity<Book> ok = ResponseEntity.ok(this.bookService.getBookById(id));
        return ok;
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/cachable")
    public void getCacheData() {
        this.cacheInspectionService.printCacheContent("books");
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userDetailsRepository.findById(id).ifPresentOrElse(user -> {
            this.userDetailsRepository.delete(user);
            System.out.println("User deleted successfully!!");
        }, () -> System.out.println("Unable to delete the user!!"));
    }
}