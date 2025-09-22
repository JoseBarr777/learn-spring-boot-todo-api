package com.example.tasksmanager.todo.service;

/**
 * Exception thrown when a Todo item cannot be found by its ID.
 */
public class TodoNotFoundException extends RuntimeException{

    /**
     * Create an exception with a message that includes the missing Todo's ID.
     */
    public TodoNotFoundException(Long id) {
        super("Todo "+ id +" not found");
    }
}
