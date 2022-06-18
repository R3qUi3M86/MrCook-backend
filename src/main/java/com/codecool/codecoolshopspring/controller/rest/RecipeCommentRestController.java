package com.codecool.codecoolshopspring.controller.rest;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.ProductComment;
import com.codecool.codecoolshopspring.model.comments.ProductCommentDTO;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import com.codecool.codecoolshopspring.model.comments.RecipeCommentDTO;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.service.ProductCommentService;
import com.codecool.codecoolshopspring.service.RecipeCommentService;
import com.codecool.codecoolshopspring.service.RecipeService;
import com.codecool.codecoolshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeCommentRestController {
    private final RecipeCommentService recipeCommentService;
    private final UserService userService;

    @PostMapping("/recipe_comment/add/{recipeId}")
    public void addRecipeComment(@RequestBody RecipeComment comment, @PathVariable String recipeId){
        User user = userService.getDefaultUser();
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
}
