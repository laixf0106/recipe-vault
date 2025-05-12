
# OLIVE Coding Assignment – Project File Documentation

## 1. Project Overview

This project is a complete full-stack recipe management system. It allows users to manage a collection of recipes, where each recipe includes attributes such as title, difficulty, image, instructions, and a list of ingredients. Users can perform CRUD operations (Create, Read, Update, Delete) via a web interface.

**Tech Stack:**
- Frontend: Vue 3 + Vite (and a static HTML fallback for offline/demo use)
- Backend: Spring Boot + JPA + Hibernate
- Database: MySQL

---

## 2. Backend Java File Explanations

### `RecipeVaultApplication.java`
Main entry point for the backend project. Contains the `public static void main(String[] args)` method and is annotated with `@SpringBootApplication`. Running this class starts the embedded Tomcat server, initializes the Spring context, and loads the application.

---

### `controller/RecipeController.java`
Defines the REST controller for handling `/recipes` HTTP endpoints using annotations like `@RestController`, `@RequestMapping`, etc.

**Responsibilities:**
- Accepts requests from frontend
- Delegates to service layer
- Returns `ResponseEntity` responses

**Endpoints:**
- GET `/recipes`
- GET `/recipes/{id}`
- POST `/recipes`
- PUT `/recipes/{id}`
- DELETE `/recipes/{id}`

---

### `dto/RecipeRequest.java`
DTO for creating or updating recipes. Receives data from the frontend.

**Fields:**
- `title` (String)
- `difficulty` (Enum or String)
- `instructions` (String)
- `imageUrl` (String)
- `creatorName` (String)
- `ingredients` (List<String>)

---

### `dto/RecipeResponse.java`
DTO for sending recipe data to the frontend.

**Includes:**
- Full recipe object with ID, title, difficulty, ingredients, and timestamp.

---

### `entity/Recipe.java`
Entity mapping to the `recipes` table.

**Fields:**
- id
- title
- difficulty
- instructions
- imageUrl
- creatorName
- createdDate

**Relation:**
- One-to-many with `Ingredient` using:
  ```java
  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  ```

---

### `entity/Ingredient.java`
Entity mapping to the `ingredients` table. Each ingredient is linked to one recipe.

**Fields:**
- id
- ingredientName
- recipe (with `@ManyToOne`)

---

### `entity/Difficulty.java`
Enum for difficulty levels:
- Easy
- Medium
- Hard

Used to enforce consistency of difficulty field in the system.

---

### `repository/RecipeRepository.java`
JPA repository for `Recipe` entity. Provides:
- save, delete, findById, findAll methods

---

### `repository/IngredientRepository.java`
JPA repository for `Ingredient` entity. Used by the service layer to manage ingredients.

---

### `service/RecipeService.java`
Service interface for recipe operations:

- `createRecipe()`
- `getAllRecipes()`
- `getRecipeById()`
- `updateRecipe()`
- `deleteRecipeById()`

---

### `service/RecipeServiceImpl.java`
Implements the service interface. Contains business logic to:

- Create and persist recipes and ingredients
- Update ingredients safely
- Convert entities to response DTOs
- Ensure transaction integrity using `@Transactional`

---

### `exception/GlobalExceptionHandler.java`
Global exception handler using `@ControllerAdvice`. Handles:

- Resource not found
- Validation errors
- Unexpected exceptions

Returns formatted error JSON to frontend.

---

### `exception/ResourceNotFoundException.java`
Custom exception thrown when a recipe is not found by ID. Handled in the global exception handler.

---

## 3. Frontend File Descriptions

### `Frontend-index.html`
Self-contained HTML + JS file that provides a full recipe UI. Can be opened directly without a server.

**Includes:**
- Table to show recipes
- Form to add/edit recipes
- API calls to backend on localhost:8080

---

## 4. Postman and Testing

`RecipeVault.postman_collection.json` contains:

- GET `/recipes`
- POST `/recipes`
- PUT `/recipes/{id}`
- DELETE `/recipes/{id}`

For quick testing of all backend functionality.

---

## 5. Database

The database is MySQL. Tables are auto-generated using JPA annotations:

- `recipes`: main table for recipe data
- `ingredients`: linked by foreign key to `recipes`

---

## 6. Deployment Notes

**Backend:**
- Run using `mvn spring-boot:run`

**Frontend:**
- Either double-click `Frontend-index.html`
- Or run `npm run dev` inside `recipe-vault-frontend`

**Optional:**
- Deploy to Vercel or Docker using provided configs

---

## Deployment Status

✅ The application has been fully developed and successfully tested in the local development environment, using a local MySQL database.

🚧 Deployment to Render (cloud platform) is currently in progress. The backend application is being configured to connect to a cloud-based database (PostgreSQL on Render) instead of the original local MySQL instance.

Expected steps:
- Cloud PostgreSQL instance setup
- Application properties updated for Render environment
- Successful deployment and verification via public API endpoint

This ensures the application is fully cloud-deployable and production-ready.

