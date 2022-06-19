package com.codecool.codecoolshopspring.service;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.model.recipes.RecipeDTO;
import com.codecool.codecoolshopspring.model.votes.RecipeVote;
import com.codecool.codecoolshopspring.model.votes.VoteType;
import com.codecool.codecoolshopspring.repository.CookingPhaseRepository;
import com.codecool.codecoolshopspring.repository.MealComponentRepository;
import com.codecool.codecoolshopspring.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
            log.warn("Cannot delete recipe id=" + id + "  - does not exist in database!");
        }
    }

    public void voteOnRecipe(long id, User user, VoteType voteType){
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        if (optRecipe.isPresent()){
            if (optRecipe.get().getUser().getId() == user.getId()){
                log.warn("Cannot vote on recipe id=" + id + " - user tried to vote on his own recipe");
                return;
            }
            List<RecipeVote> recipeVotes = optRecipe.get().getRecipeVotes();
            for (RecipeVote recipeVote : recipeVotes){
                if (recipeVote.getUser().getId() == user.getId()){
                    if (recipeVote.getVoteType() == voteType){
                        log.warn("Cannot up vote recipe id=" + id + " - user tried to cast same vote more than once");
                    } else {
                        optRecipe.get().withdrawVote(recipeVote);
                        recipeRepository.save(optRecipe.get());
                        log.info("User: "+ user.getUsername() +" withdrew vote recipe id=" + id);
                    }
                    return;
                }
            }
            optRecipe.get().castVote(user, voteType);
            recipeRepository.save(optRecipe.get());
            log.info("User: "+ user.getUsername() +" cast vote recipe id=" + id + " -> " + voteType);
        } else {
            log.warn("Cannot vote on recipe id=" + id + "  - does not exist in database!");
        }
    }
}
