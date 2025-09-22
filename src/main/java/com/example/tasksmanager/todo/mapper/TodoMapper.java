package com.example.tasksmanager.todo.mapper;

import com.example.tasksmanager.todo.domain.Todo;
import com.example.tasksmanager.todo.dto.TodoResponse;



/**
 * Converts between domain objects and DTOs.
 *
 * Right now it only maps a Todo entity -> TodoResponse,
 * since creation and updates are handled by request DTOs
 * CreateTodoRequest and UpdateTodoRequest.
 */
public final class TodoMapper {

    // Prevent instantiation because this is a utility class.
    private TodoMapper() {}

    /**
     * Turn a Todo entity into a TodoResponse for the API layer.
     */
    public static TodoResponse toResponse(Todo t) {
        return new TodoResponse(
            t.getId(),
            t.getTitle(),
            t.isCompleted(),
            t.getDueDate()
            );
  }

}
