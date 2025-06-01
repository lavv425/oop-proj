package com.devtrack.factory;

import com.devtrack.model.abstracts.Tool;

public class ToolFactory {
    public static Tool createTool(String type, String version) {
        return switch (type.toLowerCase()) {
            case "node" -> new Tool("Node.js", version) {
                @Override
                public boolean isInstalled() {
                    return true; // mock
                }
            };
            case "docker" -> new Tool("Docker", version) {
                @Override
                public boolean isInstalled() {
                    return true; // mock
                }
            };
            default -> throw new IllegalArgumentException("Unsupported tool: " + type);
        };
    }
}