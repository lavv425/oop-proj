package com.devtrack.runner;

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
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        if (System.getenv("MONGODB_URI") == null) {
            System.err.println("[Error]: Undefined environment variable MONGODB_URI.");
            System.exit(1);
        }
        cli.run();

        System.out.println("DevTrack started.");
        // Entry point: initialize CLI, load projects, etc.
    }
}