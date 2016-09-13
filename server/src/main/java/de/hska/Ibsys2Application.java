package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Ibsys2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ibsys2Application.class, args);
	}
}
