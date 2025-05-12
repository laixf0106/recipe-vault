package com.olive.recipevault.service;

import com.olive.recipevault.dto.RecipeRequest;
import com.olive.recipevault.dto.RecipeResponse;

import java.util.List;

public interface RecipeService {
    RecipeResponse createRecipe(RecipeRequest recipeRequest);
    List<RecipeResponse> getAllRecipes();
    RecipeResponse getRecipeById(Long id);
    RecipeResponse updateRecipe(Long id, RecipeRequest recipeRequest);
    void deleteRecipeById(Long id);
}
