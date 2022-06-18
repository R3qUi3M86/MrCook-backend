package com.codecool.codecoolshopspring.service;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import com.codecool.codecoolshopspring.model.comments.RecipeCommentDTO;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.repository.RecipeCommentRepository;
import com.codecool.codecoolshopspring.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeCommentService {

    private final RecipeCommentRepository recipeCommentRepository;
    private final RecipeRepository recipeRepository;

    public void createRecipeComment(RecipeComment comment, User user, long recipeId) {
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()){
            Recipe recipe = optRecipe.get();
            Optional<RecipeComment> optRecipeComment = recipeCommentRepository
                    .findRecipeCommentByUserAndRecipe(user, recipe);
            if (optRecipeComment.isEmpty()){
                comment.setUser(user);
                comment.setRecipe(recipe);
                recipeCommentRepository.save(comment);
                log.info("User commented on recipe id=" + recipeId + ": " + comment);
            } else {
                log.warn("User tried to comment on recipe more than once. Comment discarded!");
            }
        } else {
            log.warn("Recipe id=" + recipeId + " not found in database!");
        }
    }

    public List<RecipeCommentDTO> getAllRecipeComments(long recipeId){
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (optRecipe.isPresent()) {
            return optRecipe.get().getRecipeComments().stream().map(RecipeCommentDTO::new).collect(Collectors.toList());
        } else {
            log.warn("Recipe id=" + recipeId + " not found in database!");
            return new ArrayList<>();
        }
    }

    public void updateRecipeComment(RecipeComment updatedComment){
        Optional<RecipeComment> optOldComment = recipeCommentRepository.findById(updatedComment.getId());
        if (optOldComment.isPresent()){
            RecipeComment oldComment = optOldComment.get();
            oldComment.update(updatedComment);
            recipeCommentRepository.save(oldComment);
            log.info("User updated recipe comment: " + oldComment);
        } else {
            log.warn("Cannot update recipe comment id=" + updatedComment.getId() + " - does not exist in database!");
        }

    }

    public void deleteRecipeComment(long id){
        Optional<RecipeComment> optRecipeComment = recipeCommentRepository.findById(id);
        if (optRecipeComment.isPresent()){
            recipeCommentRepository.delete(optRecipeComment.get());
            log.info("User deleted recipe comment: " + optRecipeComment.get());
        } else {
            log.warn("Cannot delete recipe comment id=" + id + " - does not exist in database!");
        }
    }

//    public void upVoteRecipe(long id){
//        Optional<Recipe> optRecipe = recipeRepository.findById(id);
//        if (optRecipe.isPresent()){
//
//        }
//    }
}
