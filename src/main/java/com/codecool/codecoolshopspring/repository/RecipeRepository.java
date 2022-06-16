package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
