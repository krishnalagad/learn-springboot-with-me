package com.learnspring.reactive.repository;

import com.learnspring.reactive.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    @Query("select * from webflux_books where book_name LIKE :title")
    Flux<Book> searchBookByTitle(String title);
}