package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.comments.RecipeComment;
import com.codecool.mrcook.model.comments.RecipeCommentDTO;
import com.codecool.mrcook.model.votes.VoteType;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.RecipeCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeCommentRestController {
    private final RecipeCommentService recipeCommentService;

    @GetMapping("/recipe_comment/get_all/{recipeId}")
    public List<RecipeCommentDTO> getAllRecipeComments(@PathVariable String recipeId){
        return recipeCommentService.getAllRecipeComments(Long.parseLong(recipeId));
    }

    @PostMapping("/recipe_comment/add/{recipeId}")
    public void addRecipeComment(@RequestBody RecipeComment comment, @PathVariable String recipeId, @CurrentUser User user){
        recipeCommentService.createRecipeComment(comment, user, Long.parseLong(recipeId));
    }

    @PutMapping("/recipe_comment/update")
    public void updateRecipeComment(@RequestBody RecipeComment comment, @CurrentUser User user){
        recipeCommentService.updateRecipeComment(comment);
    }

    @DeleteMapping("/recipe_comment/delete/{id}")
    public void deleteRecipeComment(@PathVariable String id, @CurrentUser User user){
        recipeCommentService.deleteRecipeComment(Long.parseLong(id));
    }

    @PostMapping("/recipe_comment/up_vote/{id}")
    public void upVoteRecipe(@PathVariable String id, @CurrentUser User user){
        recipeCommentService.voteOnRecipeComment(Long.parseLong(id), user, VoteType.UP);
    }

    @PostMapping("/recipe_comment/down_vote/{id}")
    public void downVoteRecipe(@PathVariable String id, @CurrentUser User user){
        recipeCommentService.voteOnRecipeComment(Long.parseLong(id), user, VoteType.DOWN);
    }
}
