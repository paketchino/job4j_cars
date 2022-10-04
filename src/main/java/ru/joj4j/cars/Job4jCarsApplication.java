package ru.joj4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Job4jCarsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(Job4jCarsApplication.class);

	@Bean(destroyMethod = "close")
	public SessionFactory sf() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(Job4jCarsApplication.class, args);
		LOGGER.info("http://localhost:8080/index");
		System.out.println("http://localhost:8080/index");
	}

}
