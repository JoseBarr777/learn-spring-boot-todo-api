# Learn Spring Boot Todo API

A simple **Spring Boot** REST API for a Todo service, following a **layered architecture** (Controller → Service → Repository) with **DTOs**, **validation**, and an **H2** in-memory database.

> Built in **VS Code** using the **Spring Boot Development Pack** (Spring Initializr, Boot Dashboard, run/debug).
> [https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

## Tech Stack

Java 17 • Spring Boot 3 • Spring Web • Spring Data JPA • Jakarta Validation • Jackson • H2 (in-memory) • Maven

## Run

**CLI (Maven)**

```bash
./mvnw spring-boot:run
```

**VS Code (Dev Pack)**

1. Open the project folder in VS Code.
2. Open the **Spring Boot Dashboard** (left sidebar).
3. Click **Run** on the app to start with live reload/debug.

## H2 Console

* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:todo`
* Username: `sa` • Password: *(blank)*

## API Endpoints

| Method | Path              | Description                    |
| -----: | ----------------- | ------------------------------ |
|   POST | `/api/todos`      | Create a todo                  |
|    GET | `/api/todos`      | List todos (`?completed=true`) |
|    GET | `/api/todos/{id}` | Get by id                      |
|  PATCH | `/api/todos/{id}` | Partial update                 |
| DELETE | `/api/todos/{id}` | Delete                         |

**Example**

```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Boot","dueDate":"2025-10-01"}'

curl "http://localhost:8080/api/todos?completed=false"
```

## Project Structure

```
src/main/java/com/example/tasksmanager
└─ TasksmanagerApplication.java          # @SpringBootApplication (root)
└─ todo/
   ├─ domain/
   │  └─ Todo.java                       # entity/domain
   ├─ repo/
   │  └─ TodoRepository.java             # data access (Spring Data JPA)
   ├─ service/
   │  ├─ TodoService.java                # business logic + transactions
   │  └─ TodoNotFoundException.java      # domain/app error
   ├─ dto/
   │  ├─ CreateTodoRequest.java          # DTO (request)
   │  ├─ UpdateTodoRequest.java          # DTO (request)
   │  └─ TodoResponse.java               # DTO (response)
   ├─ mapper/
   │  └─ TodoMapper.java                 # entity ↔ DTO conversion
   └─ web/
      └─ TodoController.java             # HTTP API
```

## How it was built (timeline)

1. **Generated** a Spring Boot app (Java 17, Maven) with **Spring Web**, **Spring Data JPA**, **Validation**, and **H2** using the **VS Code Spring Boot Development Pack** (Initializr).
2. **Created** the `Todo` **entity**, a Spring Data **repository**, and a **service** with CRUD + filtering and transactions.
3. **Exposed** REST endpoints via a thin **controller**, using **DTOs** and a small **mapper** for entity↔DTO conversion (Jackson handles JSON↔DTO).
4. **Configured** H2 in-memory DB and enabled the H2 console.
5. **Verified** endpoints with `curl` (no tests in this repo).
