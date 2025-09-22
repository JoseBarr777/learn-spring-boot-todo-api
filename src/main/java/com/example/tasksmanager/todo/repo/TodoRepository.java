package com.example.tasksmanager.todo.repo;

import com.example.tasksmanager.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * Repository for Todo entities.
 *
 * Extends JpaRepository, which gives us basic CRUD + pagination/sorting
 * without writing boilerplate code. Spring Data JPA creates the actual
 * implementation at runtime and registers it as a bean, so we can just
 * inject this interface wherever we need database access.
 *
 * Custom queries can be added by following Spring Data JPA's method
 * naming conventions or with @Query annotations if needed.
 */
public interface TodoRepository extends JpaRepository <Todo, Long> {

    /**
     * Find todos by their completed status.
     * Spring Data JPA turns this into: SELECT t FROM Todo t WHERE t.completed = ?1
     */
    List <Todo> findByCompleted(boolean completed);
}