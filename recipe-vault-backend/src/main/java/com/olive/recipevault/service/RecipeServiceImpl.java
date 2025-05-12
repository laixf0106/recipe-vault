package com.olive.recipevault.service.impl;

import com.olive.recipevault.dto.RecipeRequest;
import com.olive.recipevault.dto.RecipeResponse;
import com.olive.recipevault.entity.Ingredient;
import com.olive.recipevault.entity.Recipe;
import com.olive.recipevault.repository.RecipeRepository;
import com.olive.recipevault.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public RecipeResponse createRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeRequest.getTitle());
        recipe.setDifficulty(recipeRequest.getDifficulty());
        recipe.setInstructions(recipeRequest.getInstructions());
        recipe.setImageUrl(recipeRequest.getImageUrl());
        recipe.setCreatorName(recipeRequest.getCreatorName());

        List<Ingredient> ingredients = recipeRequest.getIngredients().stream()
                .map(name -> {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIngredientName(name);
                    ingredient.setRecipe(recipe);
                    return ingredient;
                }).collect(Collectors.toList());

        recipe.setIngredients(ingredients);
        Recipe saved = recipeRepository.save(recipe);
        return toResponse(saved);
    }

    @Override
    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeResponse getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
        return toResponse(recipe);
    }

    @Override
    public RecipeResponse updateRecipe(Long id, RecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));

        recipe.setTitle(request.getTitle());
        recipe.setDifficulty(request.getDifficulty());
        recipe.setInstructions(request.getInstructions());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setCreatorName(request.getCreatorName());

        List<Ingredient> ingredients = new ArrayList<>();
        for (String name : request.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientName(name);
            ingredient.setRecipe(recipe);
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);

        Recipe saved = recipeRepository.save(recipe);
        return toResponse(saved);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    private RecipeResponse toResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .difficulty(recipe.getDifficulty())
                .instructions(recipe.getInstructions())
                .imageUrl(recipe.getImageUrl())
                .creatorName(recipe.getCreatorName())
                .createdDate(recipe.getCreatedDate())
                .ingredients(recipe.getIngredients().stream()
                        .map(Ingredient::getIngredientName)
                        .collect(Collectors.toList()))
                .build();
    }
}
