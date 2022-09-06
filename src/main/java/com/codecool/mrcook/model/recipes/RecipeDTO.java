package com.codecool.mrcook.model.recipes;

import com.codecool.mrcook.model.favourites.RecipeFavouriteDTO;
import com.codecool.mrcook.model.user.UserDTO;
import com.codecool.mrcook.model.comments.RecipeCommentDTO;
import com.codecool.mrcook.model.recipes.cookingphase.CookingPhaseDTO;
import com.codecool.mrcook.model.recipes.mealcomponent.MealComponentDTO;
import com.codecool.mrcook.model.votes.RecipeVoteDTO;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeDTO {

    private final long id;
    private final String author;
    private final String title;
    private final List<RecipeVoteDTO> recipeVotes;
    private final List<RecipeFavouriteDTO> recipeFavourites;
    private final List<MealComponentDTO> mealComponents;
    private final List<CookingPhaseDTO> cookingPhases;
    private final List<RecipeCommentDTO> recipeComments;
    private final byte[] image;
    private final Date createDate;
    private final Date modifyDate;
    private final boolean vegan;
    private final boolean vegetarian;
    private final boolean carnivore;
    private final boolean fish;
    private final boolean gluten;
    private final boolean lowFat;
    private final boolean lowCarbs;
    private final boolean hiProtein;
    private final int calories;
    private final float protein;
    private final float carbs;
    private final float fat;

    public RecipeDTO(Recipe recipe){
        this.id = recipe.getId();
        this.author = recipe.getUser().getUsername();
        this.title = recipe.getTitle();

        this.recipeVotes = recipe.getRecipeVotes().stream()
                .map(RecipeVoteDTO::new)
                .collect(Collectors.toList());

        this.recipeFavourites = recipe.getRecipeFavourites().stream()
                .map(RecipeFavouriteDTO::new)
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
        this.vegan = recipe.isVegan();
        this.vegetarian = recipe.isVegetarian();
        this.carnivore = recipe.isCarnivore();
        this.fish = recipe.isFish();
        this.gluten = recipe.isGluten();
        this.lowFat = recipe.isLowFat();
        this.lowCarbs = recipe.isLowCarbs();
        this.hiProtein = recipe.isHiProtein();
        this.calories = recipe.getCalories();
        this.protein = recipe.getProtein();
        this.carbs = recipe.getCarbs();
        this.fat = recipe.getFat();
    }
}
