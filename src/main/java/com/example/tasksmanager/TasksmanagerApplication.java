package com.example.tasksmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 *
 * The @SpringBootApplication annotation sets up component scanning,
 * auto-configuration, and allows this class to bootstrap the app.
 */
@SpringBootApplication
public class TasksmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksmanagerApplication.class, args);
	}

}
