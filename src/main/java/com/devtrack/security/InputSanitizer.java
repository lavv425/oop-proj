package com.devtrack.security;

import java.text.Normalizer;

public class InputSanitizer {
    public static String sanitize(String input) {
        if (input == null)
            return "";

        // Normalize: à -> à, ñ -> n, ecc.
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Keep only valid characters
        String filtered = normalized.replaceAll("[^a-zA-Z0-9 _.-]", "");

        // Remove extra space and trim
        String sanitizedInput = filtered.replaceAll("\\s+", " ").trim();

        return sanitizedInput;
    }
}