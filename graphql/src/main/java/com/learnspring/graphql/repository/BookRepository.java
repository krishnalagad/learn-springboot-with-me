package com.learnspring.graphql.repository;

import com.learnspring.graphql.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}