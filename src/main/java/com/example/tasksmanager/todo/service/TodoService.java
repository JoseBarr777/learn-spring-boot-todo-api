package com.example.tasksmanager.todo.service;

import com.example.tasksmanager.todo.domain.Todo;
import com.example.tasksmanager.todo.repo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Application service for Todo operations.
 *
 * Handles use-cases and coordinates with the repository.
 * Annotated as @Service so Spring can discover and inject it.
 *
 * Default @Transactional applies to all public methods (write operations),
 * with read-only overrides on query methods for clarity and performance hints.
 */
@Service 
@Transactional 
public class TodoService {

    // Spring injects a TodoRepository bean here (the runtime proxy that Spring Data creates).
    private final TodoRepository repo;

    //constructor for the service? it takes in the repo feild we declared just now. We also included the type of the Repository class. 
    // Spring sees the constructor and injects a bean of type TodoRepository
    // Spring generates a proxy class at startup that implements the interface and it gets injected here.
    public TodoService (TodoRepository repo){
        this.repo = repo;
    }

    /** Create a new todo. */
    public Todo create(String title, LocalDate dueDate) {
        return repo.save(new Todo(title, dueDate));
    }
    
    /** Fetch a todo or throw if it doesn’t exist. */
    @Transactional(readOnly = true)
    public Todo get(Long id){
        return repo.findById(id).orElseThrow(() -> new TodoNotFoundException(id)); //woah, is this like the arrow functions in js????
    }

    /** List todos, optionally filtered by completion status. */
    @Transactional(readOnly = true)
    public List<Todo> list(Boolean completed){
        return completed == null ? repo.findAll() : repo.findByCompleted(completed);
    }

    /**
     * Update selected fields on a todo.
     * Uses JPA dirty checking. The changes are flushed when the transaction commits.
     */
    public Todo update(Long id, String title, Boolean completed, LocalDate dueDate){
        var t = get(id); 

        if (title != null && !title.isBlank()) t.setTitle(title);
        if (completed != null) t.setCompleted(completed);
        if (dueDate != null) t.setDueDate(dueDate);
        return t; // no explicit save needed; managed entity will be persisted on commit
    }

    /** Delete a todo by id (404 if missing). */
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new TodoNotFoundException(id);
        repo.deleteById(id);
    }
}
