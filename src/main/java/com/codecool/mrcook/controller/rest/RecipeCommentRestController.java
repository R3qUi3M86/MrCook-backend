package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.comments.RecipeComment;
import com.codecool.mrcook.model.comments.RecipeCommentDTO;
import com.codecool.mrcook.model.votes.VoteType;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.RecipeCommentService;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeCommentRestController {
    private final RecipeCommentService recipeCommentService;
    private final UserService userService;

    @PostMapping("/recipe_comment/add/{recipeId}")
    public void addRecipeComment(@RequestBody RecipeComment comment, @PathVariable String recipeId, @CurrentUser User user){
        recipeCommentService.createRecipeComment(comment, user, Long.parseLong(recipeId));
    }

    @GetMapping("/recipe_comment/get_all/{recipeId}")
    public List<RecipeCommentDTO> getAllRecipeComments(@PathVariable String recipeId){
        return recipeCommentService.getAllRecipeComments(Long.parseLong(recipeId));
    }

    @PutMapping("/recipe_comment/update")
    public void updateRecipeComment(@RequestBody RecipeComment comment){
        recipeCommentService.updateRecipeComment(comment);
    }

    @DeleteMapping("/recipe_comment/delete/{id}")
    public void deleteRecipeComment(@PathVariable String id){
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
