package com.learnspring.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;

@Service
public class FluxService {

    // return publisher -> Flux
    public Flux<String> getFlux() {
        return Flux.just("DEFAULT", "FLUX").log();
    }

    // return publisher -> Flux from any collection
    public Flux<String> getFluxFromCollection() {
        List<String> fruits = List.of("Mango", "Cherry", "Banana", "Apple", "Cranberry", "Grape", "Jackfruit");
        return Flux.fromIterable(fruits).log();
    }

    public Flux<String> getBlankFlux() {
        return Flux.empty();
    }

    // map() -> To operate on each item in Flux
    public Flux<String> mapExampleFlux() {
        Flux<String> flux = getFluxFromCollection().map(item -> {
            String str = item.toUpperCase();
            return str;
        });
        return flux;
    }

    // filter() ->
    public Flux<String> filterExampleFlux() {
        Flux<String> flux = getFluxFromCollection().filter(item -> {
            boolean b = item.length() > 5;
            return b;
        });
        return flux;
    }
}