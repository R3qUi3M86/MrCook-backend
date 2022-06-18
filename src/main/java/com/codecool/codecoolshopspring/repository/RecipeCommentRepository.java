package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {

    Optional<RecipeComment> findRecipeCommentByUserAndRecipe(User user, Recipe recipe);
    List<RecipeComment> findByRecipe(Recipe recipe);
}
