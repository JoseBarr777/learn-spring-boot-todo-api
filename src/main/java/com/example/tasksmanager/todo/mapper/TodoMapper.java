package com.example.tasksmanager.todo.mapper;

import com.example.tasksmanager.todo.domain.Todo;
import com.example.tasksmanager.todo.dto.TodoResponse;


public final class TodoMapper {

    // private constructor to prevent instantiation (utility class)
    // this means that this class is not meant to be instantiated, as it only contains static methods.
    private TodoMapper() {}

    // Maps a Todo entity to a TodoResponse DTO. 
    // We dont't need to map in the other direction because we use CreateTodoRequest for that purpose.
    public static TodoResponse toResponse(Todo t) {
        return new TodoResponse(
            t.getId(),
            t.getTitle(),
            t.isCompleted(),
            t.getDueDate()
            );
  }

}
