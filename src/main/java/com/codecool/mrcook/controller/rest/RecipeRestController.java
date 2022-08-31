package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.recipes.Recipe;
import com.codecool.mrcook.model.recipes.RecipeDTO;
import com.codecool.mrcook.model.votes.VoteType;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeRestController {

    private final RecipeService recipeService;

    @GetMapping("/recipes/get/{id}")
    public RecipeDTO getRecipe(@PathVariable String id){
        return recipeService.getRecipe(Long.parseLong(id));
    }

    @GetMapping("/recipes/get_all")
    public List<RecipeDTO> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @PostMapping("/recipes/add")
    public void addRecipe(@RequestBody Recipe recipe, @CurrentUser User user){
        recipeService.createUserRecipe(recipe, user);
    }

    @PutMapping("/recipes/update")
    public void updateRecipe(@RequestBody Recipe recipe, @CurrentUser User user){
        recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/recipes/delete/{id}")
    public void deleteRecipe(@PathVariable String id, @CurrentUser User user){
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

    @PostMapping("/recipes/add_favourite/{id}")
    public ResponseEntity<?> removeRecipeFavourite(@PathVariable String id, @CurrentUser User user){
        if(recipeService.addRecipeToFavourites(Long.parseLong(id), user))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("/recipes/remove_favourite/{id}")
    public ResponseEntity<?> addRecipeFavourite(@PathVariable String id, @CurrentUser User user){
        if(recipeService.removeRecipeFromFavourites(Long.parseLong(id), user))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
