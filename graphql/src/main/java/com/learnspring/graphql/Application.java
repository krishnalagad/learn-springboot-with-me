package com.learnspring.graphql;

import com.learnspring.graphql.entity.Book;
import com.learnspring.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.learnspring.graphql.mongorepo"})
@EnableJpaRepositories(basePackages = {"com.learnspring.graphql.repository"})
public class Application implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.addData();

		System.out.println("Project started..");
	}

	private void addData() {
		List<Book> books = IntStream.rangeClosed(1, 20)
				.mapToObj(index -> new Book(0, "Book" + index, "Great Book", "KrishnaAakanksha",
						new Random().nextDouble(23000), new Random().nextInt(500))).toList();
		this.bookRepository.saveAll(books);
	}
}