package com.devtrack.model.abstracts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolTest {
    static class TestToolMock extends Tool {
        public TestToolMock(String name, String version) {
            super(name, version);
        }

        @Override
        public boolean isInstalled() {
            return true;
        }
    }

    @Test
    void constructor_shouldInitializeFields() {
        Tool tool = new TestToolMock("Node.js", "20.0.0");

        assertEquals("Node.js", tool.getName());
        assertEquals("20.0.0", tool.getVersion());
    }

    @Test
    void isInstalled_shouldReturnTrueInTestToolMock() {
        Tool tool = new TestToolMock("Git", "20.0.0");

        assertTrue(tool.isInstalled());
    }
}