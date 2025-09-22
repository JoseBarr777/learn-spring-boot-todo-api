package com.example.tasksmanager.todo.web;

import com.example.tasksmanager.todo.dto.CreateTodoRequest;
import com.example.tasksmanager.todo.dto.TodoResponse;
import com.example.tasksmanager.todo.dto.UpdateTodoRequest;

import com.example.tasksmanager.todo.mapper.TodoMapper;

import com.example.tasksmanager.todo.service.TodoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Todo resources.
 *
 * Exposes CRUD endpoints under /api/todos.
 * Delegates business logic to TodoService and converts
 * entities into DTOs for the API layer.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    //fields
    private final TodoService service;
    
    //constructor
    public TodoController(TodoService service) {
        this.service = service;
    }

    /** Create a new todo item. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest req) {
        return TodoMapper.toResponse(service.create(req.title(), req.dueDate()));
    }

    /** List all todos, optionally filtered by completion status. */
    @GetMapping
    public List<TodoResponse> list(@RequestParam(required = false) Boolean completed) {
        return service.list(completed).stream().map(TodoMapper::toResponse).toList();
    }

    /** Get a single todo by id. */
    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return TodoMapper.toResponse(service.get(id));
    }

    /** Partially update a todo (title, status, or due date). */
    @PatchMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody UpdateTodoRequest req) {
        return TodoMapper.toResponse(
        service.update(id, req.title(), req.completed(), req.dueDate())
        );
    }

    /** Delete a todo by id. */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}