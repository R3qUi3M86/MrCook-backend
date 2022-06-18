package com.codecool.codecoolshopspring.service;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.model.recipes.RecipeDTO;
import com.codecool.codecoolshopspring.repository.CookingPhaseRepository;
import com.codecool.codecoolshopspring.repository.MealComponentRepository;
import com.codecool.codecoolshopspring.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final MealComponentRepository mealComponentRepository;
    private final CookingPhaseRepository cookingPhaseRepository;

    public void createUserRecipe(Recipe recipe, User user) {
        recipe.setUser(user);
        recipe.setMealComponentsRelation();
        recipe.setCookingPhasesRelation();
        recipeRepository.save(recipe);
        log.info("User added new recipe: " + recipe);
    }

    public RecipeDTO getRecipe(long id){
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        if (optRecipe.isPresent()){
            return new RecipeDTO(optRecipe.get());
        } else {
            log.warn("Recipe id=" + id + " not found in database!");
            return null;
        }
    }

    public void updateRecipe(Recipe updatedRecipe){
        Optional<Recipe> optOldRecipe = recipeRepository.findById(updatedRecipe.getId());
        if (optOldRecipe.isPresent()){
            Recipe oldRecipe = optOldRecipe.get();
            oldRecipe.update(updatedRecipe);
            recipeRepository.save(oldRecipe);
            log.info("User updated recipe: " + oldRecipe);
        } else {
            log.warn("Cannot update recipe id=" + updatedRecipe.getId() +" - does not exist in database!");
        }

    }

    public void deleteRecipe(long id){
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        if (optRecipe.isPresent()){
            log.info("User deleted recipe: " + optRecipe.get());
            recipeRepository.deleteById(id);
            mealComponentRepository.deleteAll(optRecipe.get().getMealComponents());
            cookingPhaseRepository.deleteAll(optRecipe.get().getCookingPhases());
        } else {
            log.warn("Cannot delete recipe id="+ id +"  - does not exist in database!");
        }
    }

    public void upVoteRecipe(long id){
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        if (optRecipe.isPresent()){

        }
    }
}
