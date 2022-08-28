package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.recipes.Recipe;
import com.codecool.mrcook.model.recipes.RecipeDTO;
import com.codecool.mrcook.model.votes.VoteType;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.RecipeService;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeRestController {

    private final RecipeService recipeService;
    private final UserService userService;

    @PostMapping("/recipes/add")
    public void addRecipe(@RequestBody Recipe recipe, @CurrentUser User user){
        recipeService.createUserRecipe(recipe, user);
    }

    @GetMapping("/recipes/get/{id}")
    public RecipeDTO getRecipe(@PathVariable String id){
        return recipeService.getRecipe(Long.parseLong(id));
    }

    @PutMapping("/recipes/update")
    public void updateRecipe(@RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/recipes/delete/{id}")
    public void deleteRecipe(@PathVariable String id){
        recipeService.deleteRecipe(Long.parseLong(id));
    }

    @PostMapping("/recipes/up_vote/{id}")
    public void upVoteRecipe(@PathVariable String id, @CurrentUser User user){
        recipeService.voteOnRecipe(Long.parseLong(id), user, VoteType.UP);
    }

    @PostMapping("/recipes/down_vote/{id}")
    public void downVoteRecipe(@PathVariable String id, @CurrentUser User user){
        recipeService.voteOnRecipe(Long.parseLong(id), user, VoteType.DOWN);
    }
}
