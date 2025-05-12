package com.olive.recipevault.repository;

import com.olive.recipevault.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient i WHERE i.recipe.id = :recipeId")
    void deleteByRecipeId(Long recipeId);
}
