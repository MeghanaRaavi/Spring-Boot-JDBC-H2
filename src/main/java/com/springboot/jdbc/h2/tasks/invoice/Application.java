package com.springboot.jdbc.h2.tasks.invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InvoiceRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Invoice id 12345 -> {}", repository.findById(12345L));

        logger.info("Inserting -> {}", repository.insert(new Invoice(134646L, "Tata", "GSTU34657")));

        logger.info("Update 127378 -> {}", repository.update(new Invoice(12345L, "Name-Updated", "657HSVDG")));

        repository.deleteById(324567L);

        logger.info("All users -> {}", repository.findAll());
    }
}
