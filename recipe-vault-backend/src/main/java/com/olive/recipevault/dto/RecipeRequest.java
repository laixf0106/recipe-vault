package com.olive.recipevault.dto;

import com.olive.recipevault.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * DTO for creating or updating a recipe.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeRequest {
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdDate;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    private String instructions;

    private String imageUrl;

    @NotBlank(message = "Creator name is required")
    private String creatorName;

    private List<String> ingredients;
}