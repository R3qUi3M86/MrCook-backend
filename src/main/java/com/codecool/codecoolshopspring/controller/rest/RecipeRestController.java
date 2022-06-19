package com.codecool.codecoolshopspring.controller.rest;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.model.recipes.RecipeDTO;
import com.codecool.codecoolshopspring.model.votes.VoteType;
import com.codecool.codecoolshopspring.service.RecipeService;
import com.codecool.codecoolshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeRestController {

    private final RecipeService recipeService;
    private final UserService userService;

    @PostMapping("/recipes/add")
    public void addRecipe(@RequestBody Recipe recipe){
        User user = userService.getDefaultAdminUser();
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
    public void upVoteRecipe(@PathVariable String id){
        User user = userService.getDefaultCustomerUser();
        recipeService.voteOnRecipe(Long.parseLong(id), user, VoteType.UP);
    }

    @PostMapping("/recipes/down_vote/{id}")
    public void downVoteRecipe(@PathVariable String id){
        User user = userService.getDefaultCustomerUser();
        recipeService.voteOnRecipe(Long.parseLong(id), user, VoteType.DOWN);
    }
}
