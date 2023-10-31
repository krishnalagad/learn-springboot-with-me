package com.learnspring.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMono() {
		// Mono -> Publisher that have 0..1 items.
		// created Mono
		Mono<String> error = Mono.error(new RuntimeException("ERROR !!"));
		Mono<String> m1 = Mono.just("My name is Krishna Lagad")
				.log()
				.then(error);

		// consume Mono by subscribing it.
		m1.subscribe(data -> {
			System.out.println("Data: " + data);
		});
		error.subscribe(System.out::println);
	}

	@Test
	void testMonoMethods() {

		// create Mono
		Mono<String> m1 = Mono.just("Krishna is my name");
		Mono<String> m2 = Mono.just("I live in Pune");
		Mono<Integer> m3 = Mono.just(987634);

		// Merge available Mono
		Mono<Tuple3<String, String, Integer>> mergedMono = Mono.zip(m1, m2, m3);
		mergedMono.subscribe(data -> {
			System.out.println(data);
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});

		// Merge Mono with current Mono
		Mono<Tuple2<String, String>> zipWithMono = m1.zipWith(m2);
		zipWithMono.subscribe(data -> {
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});

		// map() Mono -> return value of Mono
		Mono<String> resultMapMono = m1.map(item -> {
			String str = item.toUpperCase();
			return str;
		});
		resultMapMono.subscribe(System.out::println);

		// flatMap() -> return value of Mono as another Mono
		Mono<String[]> resultFlatMapMono = m1.flatMap(valueMono1 -> {
			Mono<String[]> mono = Mono.just(valueMono1.trim().split(" "));
			return mono;
		});
		resultFlatMapMono.subscribe(data -> {
			System.out.println("flatMap(): " + Arrays.stream(data).toList());
		});

		// flatMapMany() -> return Flux from the Mono
		Flux<String> resultFlux = m1.flatMapMany(valueMono1 -> {
			Flux<String> flux = Flux.just(valueMono1.split(" "));
			return flux;
		}).log();
		resultFlux.subscribe(data -> {
			System.out.println("flatMapMany(): " + data);
		});

		// concatWith() -> concat Mono's and return Flux
		Flux<String> stringFlux = m1.concatWith(m2).log();
		stringFlux.subscribe(data -> {
			System.out.println("concatWith(): " + data);
		});
	}

}