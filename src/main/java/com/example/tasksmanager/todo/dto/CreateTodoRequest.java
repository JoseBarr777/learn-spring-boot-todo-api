package com.example.tasksmanager.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;



/**
 * Request object for creating a new Todo item.
 *
 * This is a record (immutable data carrier) that is used for requests
 * because the data shouldn’t change once it comes in.
 *
 * - Validation annotations (@NotBlank, @Size) make sure the title is present
 *   and not too long.
 * - @JsonFormat tells Jackson how to handle the date when converting to/from JSON.
 *
 * Note: This isn’t marked as a Spring bean (@Component, @Service, etc.)
 * since it’s just a simple data holder, not part of the application context.
 */
public record CreateTodoRequest(
  @NotBlank 
  @Size(max = 200)
  String title,
  
  @JsonFormat(pattern = "yyyy-MM-dd") 
  LocalDate dueDate
) {}