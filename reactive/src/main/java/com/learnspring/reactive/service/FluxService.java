package com.learnspring.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

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

    // flatMap() ->
    public Flux<String> flatMapExampleFlux() {
        Flux<String> stringFlux = getFluxFromCollection().flatMap(item -> {
            Flux<String> flux = Flux.just(item.split(""));
            return flux;
        }).delayElements(Duration.ofSeconds(1)).log();
        return stringFlux;
    }

    // filter() ->
    public Flux<String> filterExampleFlux() {
        Flux<String> flux = getFluxFromCollection().filter(item -> {
            boolean b = item.length() > 5;
            return b;
        });
        return flux;
    }

    // transform() ->
    public Flux transformExample() {
        Function<Flux<String>, Flux<String>> functionalInterface = (name) -> name.map(String::toUpperCase);
        return getFluxFromCollection().transform(functionalInterface).log();
    }

    public Flux<String> ifExample(int len) {
        Flux<String> flux = getFluxFromCollection().filter(name -> {
            boolean b = name.length() > len;
            return b;
        }).defaultIfEmpty("No Name Found").switchIfEmpty(getFlux()).log();
        return flux;
    }

    // concat, merge, zip impl pending
}