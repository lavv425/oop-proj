package com.devtrack.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputSanitizerTest {

    @Test
    void sanitize_shouldRemoveSpecialCharacters() {
        String input = "Test@Input!$%^";
        String sanitized = InputSanitizer.sanitize(input);
        assertEquals("TestInput", sanitized);
    }

    @Test
    void sanitize_shouldKeepAllowedCharacters() {
        String input = "Task 1 - Test_v2.0";
        String sanitized = InputSanitizer.sanitize(input);
        assertEquals("Task 1 - Test_v2.0", sanitized);
    }

    @Test
    void sanitize_shouldReturnEmptyString_ifOnlySpecialCharacters() {
        String input = "#!@*";
        String sanitized = InputSanitizer.sanitize(input);
        assertEquals("", sanitized);
    }
}