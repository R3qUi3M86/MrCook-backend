package com.codecool.mrcook.repository;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.comments.RecipeComment;
import com.codecool.mrcook.model.recipes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {

    Optional<RecipeComment> findRecipeCommentByUserAndRecipe(User user, Recipe recipe);
    List<RecipeComment> findByRecipe(Recipe recipe);
}
