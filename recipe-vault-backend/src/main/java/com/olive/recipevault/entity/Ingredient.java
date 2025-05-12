package com.olive.recipevault.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing an Ingredient.
 */
@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
