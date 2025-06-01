package com.devtrack.security;

public class ExceptionShield {
    public static void handle(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
    }
}