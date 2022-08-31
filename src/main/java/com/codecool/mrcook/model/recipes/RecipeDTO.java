package com.codecool.mrcook.model.recipes;

import com.codecool.mrcook.model.user.UserDTO;
import com.codecool.mrcook.model.comments.RecipeCommentDTO;
import com.codecool.mrcook.model.recipes.cookingphase.CookingPhaseDTO;
import com.codecool.mrcook.model.recipes.mealcomponent.MealComponentDTO;
import com.codecool.mrcook.model.votes.RecipeVoteDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeDTO {

    private final long id;
    private final UserDTO userDTO;
    private final String title;
    private final List<RecipeVoteDTO> recipeVotes;
    private final List<MealComponentDTO> mealComponents;
    private final List<CookingPhaseDTO> cookingPhases;
    private final List<RecipeCommentDTO> recipeComments;
    private final byte[] image;
    private final Date createDate;
    private final Date modifyDate;

    public RecipeDTO(Recipe recipe){
        this.id = recipe.getId();
        this.userDTO = new UserDTO(recipe.getUser());
        this.title = recipe.getTitle();

        this.recipeVotes = recipe.getRecipeVotes().stream()
                .map(RecipeVoteDTO::new)
                .collect(Collectors.toList());

        this.mealComponents = recipe.getMealComponents().stream()
                .map(MealComponentDTO::new)
                .collect(Collectors.toList());

        this.cookingPhases = recipe.getCookingPhases().stream()
                .map(CookingPhaseDTO::new)
                .collect(Collectors.toList());

        this.recipeComments = recipe.getRecipeComments().stream()
                .map(RecipeCommentDTO::new)
                .collect(Collectors.toList());

        this.image = recipe.getImage();
        this.createDate = recipe.getCreateDate();
        this.modifyDate = recipe.getModifyDate();
    }
}
