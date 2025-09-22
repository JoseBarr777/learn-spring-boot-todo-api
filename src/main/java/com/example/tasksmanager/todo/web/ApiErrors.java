package com.example.tasksmanager.todo.web;

import com.example.tasksmanager.todo.service.TodoNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ApiErrors {
  @ExceptionHandler(TodoNotFoundException.class)
  ProblemDetail notFound(TodoNotFoundException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }
}