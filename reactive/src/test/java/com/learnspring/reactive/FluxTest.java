package com.learnspring.reactive;

import com.learnspring.reactive.service.FluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FluxTest {

    @Autowired
    private FluxService fluxService;

    @Test
    void fluxTest1() {
        this.fluxService.getFlux().subscribe(data -> {
            System.out.println(data);
            System.out.println("Done with flux data.");
        });
    }

    @Test
    void fluxTest2() {
        this.fluxService.getFluxFromCollection().subscribe(data -> {
            System.out.println(data);
            System.out.println("Done with flux data.");
        });
    }

    @Test
    void fluxTest3() {
        this.fluxService.mapExampleFlux().subscribe(System.out::println);
    }
}