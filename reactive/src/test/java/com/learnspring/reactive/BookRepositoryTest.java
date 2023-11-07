package com.learnspring.reactive;

import com.learnspring.reactive.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFinderMethod() {
        this.bookRepository.searchBookByTitle("%Java%")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

}