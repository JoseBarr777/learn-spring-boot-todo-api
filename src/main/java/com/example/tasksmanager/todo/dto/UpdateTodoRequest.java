package com.example.tasksmanager.todo.dto;

import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * Request object for updating an existing Todo.
 * 
 * Fields are optional so clients can send partial updates.
 * - Title: must be 200 characters or less if present.
 * - Completed: true/false flag, nullable if unchanged.
 * - DueDate: formatted as yyyy-MM-dd for JSON serialization.
 */
public record UpdateTodoRequest(
    @Size(max = 200)
    String title,

    Boolean completed,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dueDate
) {}