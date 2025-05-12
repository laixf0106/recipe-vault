package com.olive.recipevault.dto;

import com.olive.recipevault.entity.Difficulty;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for returning recipe details.
 */
@Builder
@Data
public class RecipeResponse {

    private Long id;

    private String title;

    private Difficulty difficulty;

    private String instructions;

    private String imageUrl;

    private String creatorName;

    private LocalDateTime createdDate;

    private List<String> ingredients;
}
