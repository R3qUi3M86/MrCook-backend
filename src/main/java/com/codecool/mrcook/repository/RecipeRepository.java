package com.codecool.mrcook.repository;

import com.codecool.mrcook.model.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
