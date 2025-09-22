package com.example.tasksmanager.todo.dto;

import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UpdateTodoRequest(
    @Size(max = 200)
    String title,

    Boolean completed,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dueDate
) {}