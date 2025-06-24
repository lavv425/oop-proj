package com.devtrack.runner;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.devtrack")
@EnableMongoRepositories(basePackages = "com.devtrack.repository")
public class Application implements CommandLineRunner {
    private final DevTrackCLI cli;

    @Autowired
    public Application(DevTrackCLI cli) {
        this.cli = cli;
    }

    public static void main(String[] args) {
        loadProperties();
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        cli.run();

        System.out.println("DevTrack started.");
        // Entry point: initialize CLI, load projects, etc.
    }

    private static void loadProperties() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGODB_URI"));
    }
}