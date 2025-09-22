package com.example.tasksmanager.todo.dto;

import java.time.LocalDate;


/**
 * Response object returned when sending a Todo back to the client.
 * 
 * Contains the task’s id, title, completion status, and due date.
 * Used only for output (read-only snapshot of the entity).
 */
public record TodoResponse(
    Long id,
    String title,
    boolean completed,
    LocalDate dueDate
) {}