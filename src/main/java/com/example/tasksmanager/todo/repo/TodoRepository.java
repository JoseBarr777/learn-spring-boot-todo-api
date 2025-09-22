package com.example.tasksmanager.todo.repo;

import com.example.tasksmanager.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository provides basic CRUD operations and pagination/sorting capabilities.
// By extending JpaRepository, TodoRepository inherits these methods for free.
// The first generic parameter is the type of the entity (Todo), and the second is the type of the entity's ID (Long).
// This interface can also define custom query methods by following Spring Data JPA's naming conventions.
// Spring Data JPA will automatically provide the implementation for this interface at runtime.
// No need to annotate with @Repository, as Spring Data JPA handles that automatically.
// JPA will create a proxy instance of this interface and manage it as a Spring bean.
// This allows you to inject TodoRepository into other components (like services or controllers) and use it to interact with the database.
// The actual implementation is generated at runtime by Spring Data JPA.
// use annotations to define custom queries if needed, but for basic CRUD operations, no additional annotations are necessary.
public interface TodoRepository extends JpaRepository <Todo, Long> {
     
    // custom query method to find todos by their completed status. follows Spring Data JPA naming conventions. creates a query like "SELECT t FROM Todo t WHERE t.completed = ?1"
    List <Todo> findByCompleted(boolean completed);
}