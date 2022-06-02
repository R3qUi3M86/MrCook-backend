package com.codecool.codecoolshopspring.controller.rest;

import com.codecool.codecoolshopspring.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecipeRestController {

    private final RecipeService recipeService;

    @PostMapping("/recipes/addRecipe")
    public void addRecipe(@RequestBody Map<String, Object> payload){
    }

    @DeleteMapping("/recipes/deleteRecipe")
    public void deleteRecipe(@RequestBody Map<String, Object> payload){
    }
}
