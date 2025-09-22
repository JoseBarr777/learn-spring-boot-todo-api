package com.example.tasksmanager.todo.web;

import com.example.tasksmanager.todo.service.TodoNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Centralized API error handling.
 *
 * Maps exceptions to HTTP responses so controllers stay clean.
 */
@RestControllerAdvice
class ApiErrors {

   /**
   * Handle TodoNotFoundException by returning a 404 response
   * with a ProblemDetail payload.
   */
  @ExceptionHandler(TodoNotFoundException.class)
  ProblemDetail notFound(TodoNotFoundException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }
}