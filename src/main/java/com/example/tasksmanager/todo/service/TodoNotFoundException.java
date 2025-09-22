package com.example.tasksmanager.todo.service;

// Custom exception for handling cases where a Todo item is not found.
// It performs the business logic of throwing an exception when a Todo item is not found.
public class TodoNotFoundException extends RuntimeException{

    // Constructor that takes the ID of the Todo item that was not found.
    public TodoNotFoundException(Long id) {
        super("Todo "+ id +" not found"); // Calls the superclass constructor with a custom error message.
    }
}
