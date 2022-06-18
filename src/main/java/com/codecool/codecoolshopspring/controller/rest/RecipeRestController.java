package com.codecool.codecoolshopspring.controller.rest;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.recipes.MealComponent;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.model.recipes.RecipeDTO;
import com.codecool.codecoolshopspring.service.RecipeService;
import com.codecool.codecoolshopspring.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeRestController {

    private final RecipeService recipeService;
    private final UserService userService;

    @PostMapping("/recipes/add")
    public void addRecipe(@RequestBody Recipe recipe){
        User user = userService.getDefaultUser();
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
}
