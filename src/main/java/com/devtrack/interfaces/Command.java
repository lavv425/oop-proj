package com.devtrack.interfaces;

/**
 * Represents a command that can be executed.
 * 
 * This functional interface defines a single method for executing a command,
 * allowing for simple, concise implementation of executable actions.
 */
@FunctionalInterface
public interface Command {
    void execute();
}