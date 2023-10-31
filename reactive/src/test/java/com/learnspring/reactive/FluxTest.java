package com.learnspring.reactive;

import com.learnspring.reactive.service.FluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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
        Flux<String> flux = this.fluxService.mapExampleFlux();
        StepVerifier.create(flux)
                .expectNextCount(7)
                .verifyComplete();
        StepVerifier.create(flux)
                .expectNext("Mango".toUpperCase(), "Cherry".toUpperCase(), "Banana".toUpperCase(),
                        "Apple".toUpperCase(), "Cranberry".toUpperCase(), "Grape".toUpperCase(),
                        "Jackfruit".toUpperCase())
                .verifyComplete();
    }

    @Test
    void fluxTest4() {
        Flux<String> flux = this.fluxService.filterExampleFlux();
        StepVerifier.create(flux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fluxTest5() {
        Flux<String> flux = this.fluxService.flatMapExampleFlux();
        StepVerifier.create(flux)
                .expectNextCount(45)
                .verifyComplete();
    }
}