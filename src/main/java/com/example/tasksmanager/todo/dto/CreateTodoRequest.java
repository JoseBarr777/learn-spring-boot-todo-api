package com.example.tasksmanager.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;


// DTO for creating a new Todo item
// Jackson is used for JSON serialization/deserialization
// Jackarta Bean Validation annotations are used for input validation
// Records are immutable data carriers introduced in Java 14. THey provide a concise syntax for declaring classes which are transparent holders for shallowly immutable data.
// not marked as a bean with @Component or @Service because it's just a data carrier, not a service or component in the application context.
public record CreateTodoRequest(
  @NotBlank 
  @Size(max = 200)
  String title,
  
  @JsonFormat(pattern = "yyyy-MM-dd") 
  LocalDate dueDate
) {}