package com.olive.recipevault.repository;

import com.olive.recipevault.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository for Recipe entity.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r ORDER BY r.createdDate DESC")
    List<Recipe> findAllOrderByCreatedDateDesc();
}
