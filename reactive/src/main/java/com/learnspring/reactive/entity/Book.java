package com.learnspring.reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("webflux_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @Column("book_id")
    private Integer bookId;

    @Column("book_name")
    private String name;

    @Column("book_desc")
    private String description;

    @Column("book_publisher")
    private String publisher;

    @Column("book_author")
    private String author;
}