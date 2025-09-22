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

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    //fields
    private final TodoService service;
    
    //constructor
    public TodoController(TodoService service) {
        this.service = service;
    }

    //create a todo list item
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest req) {
        return TodoMapper.toResponse(service.create(req.title(), req.dueDate()));
    }

    //get the list of todo items
    @GetMapping
    public List<TodoResponse> list(@RequestParam(required = false) Boolean completed) {
        return service.list(completed).stream().map(TodoMapper::toResponse).toList();
    }

    // get individual details about a todo item
    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return TodoMapper.toResponse(service.get(id));
    }

    @PatchMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody UpdateTodoRequest req) {
        return TodoMapper.toResponse(
        service.update(id, req.title(), req.completed(), req.dueDate())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}