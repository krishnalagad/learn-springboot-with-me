package com.learnspring.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("webflux_books")
public class Book {

    @Id
    private Integer bookId;
    private String name;
    private String description;
    private String publisher;
    private String author;
}