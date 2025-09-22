package com.example.tasksmanager.todo.service;

import com.example.tasksmanager.todo.domain.Todo;
import com.example.tasksmanager.todo.repo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import javax.swing.Spring;


@Service //annotation to mark it as a sevrvice for use in the controller layer. 
@Transactional // Declares a default transaction boundary for all public methods of the class. Spring wraps the bean in a transactional proxy. When a public method is invoked from outside the bean, the proxy starts/joins a transaction, binds an EntityManager (persistence context), and commits/rolls back at the end.
public class TodoService {
    // how does this create an instance of a class? Especially if the interface is used to crate a proxy object?
    // Or does it call the proxy object? WHen does it cleanup after it's done using the object?
    //the service itself is a singleton and lives for the app’s lifetime. PA resources (like the EntityManager / persistence context) are created and closed per transaction/request, not per service.
    private final TodoRepository repo;

    //constructor for the service? it takes in the repo feild we declared just now. We also included the type of the Repository class. 
    // Spring sees the constructor and injects a bean of type TodoRepository
    // Spring generates a proxy class at startup that implements the interface and it gets injected here.
    public TodoService (TodoRepository repo){
        this.repo = repo;
    }

    //creating an entry for the todo list.
    public Todo create(String title, LocalDate dueDate) {
        return repo.save(new Todo(title, dueDate));
    }

    @Transactional(readOnly = true)
    public Todo get(Long id){
        return repo.findById(id).orElseThrow(() -> new TodoNotFoundException(id)); //woah, is this like the arrow functions in js????
    }

    @Transactional(readOnly = true)
    public List<Todo> list(Boolean completed){
        return completed == null ? repo.findAll() : repo.findByCompleted(completed);
    }

    public Todo update(Long id, String title, Boolean completed, LocalDate dueDate){
        var t = get(id); //when did java get var as a keyword? Is it like var in js? I thought java was strongly typed?
        // do we call var since the get() method is declared outside this block of code (method)? How is this handled during
        // compile time and runtime? Is it like JS where it holds a reference to the function and this only works becuase the 
        // get() method was declared before this?

        //Java lambda, analogous to JS arrow functions syntactically, but it’s strongly typed and targets a specific functional interface (Supplier<Throwable> here). It’s not the same runtime model as JS.
        //var is still statically typed. The compiler infers the type from the initializer

        if (title != null && !title.isBlank()) t.setTitle(title);
        if (completed != null) t.setCompleted(completed);
        if (dueDate != null) t.setDueDate(dueDate);
        return t; // JPA dirty-checking will flush on txn commit
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new TodoNotFoundException(id);
        repo.deleteById(id);
    }
}
