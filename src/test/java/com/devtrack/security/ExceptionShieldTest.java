package com.devtrack.security;

import org.junit.jupiter.api.Test;

class ExceptionShieldTest {

    @Test
    void handle_shouldNotThrowException() {
        Exception e = new RuntimeException("Test error");
        // Just need to know that the method doesn't throw an exception
        ExceptionShield.handle(e);
    }
}