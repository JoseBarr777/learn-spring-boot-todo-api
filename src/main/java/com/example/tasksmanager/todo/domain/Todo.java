package com.example.tasksmanager.todo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

/** 
 * The Todo class represents a task in the to-do list application.
 * 
 * Note: This class is a JPA entity that maps to the "todos" table in the database.
 * It includes fields for id, title, completed status, and due date.
 * It also provides constructors, getters, setters, and a method to toggle the completed status.
 * 
 * Annotations are used to define how the class and its fields map to the database schema.
 * Constructors and methods are provided to manage the state and behavior of a Todo object.
 * 
 * Entities don’t just hold data; they can also have behaviors that make sense in the business domain.
 * That’s why some people prefer to call them domain entities instead of just “data models.”
 */

@Entity // tells JPA that this it should manage this
@Table(name = "todos") // syas which table it should map to
public class Todo {

    //fields: think of it like a regular fields where the annotations make JPA add whatever contraints it has. THIS IS THE ORM LANGUAGE!
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private boolean completed = false;

    private LocalDate dueDate;

    // JPA requires a no-argument constructor so it can create objects when loading from the database.
    // marking it protected prevents it from being used outside the class but still allows JPA to use it.
    protected Todo() {} // JPA only

    public Todo(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    // getters 
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // tiny domain behaviors (domain logic). Remember, objects are both data and behavior.
    public void toggle() {
        this.completed = !this.completed;
    }
}
