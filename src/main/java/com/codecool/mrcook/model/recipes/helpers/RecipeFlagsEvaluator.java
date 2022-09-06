package com.codecool.mrcook.model.recipes.helpers;

import com.codecool.mrcook.model.recipes.Ingredient;
import com.codecool.mrcook.model.recipes.IngredientType;
import com.codecool.mrcook.model.recipes.Recipe;
import com.codecool.mrcook.model.recipes.mealcomponent.MealComponent;

import java.util.List;

public class RecipeFlagsEvaluator {
    public static void updateSearchFlags(Recipe recipe){
        updateVegan(recipe);
        updateVegetarian(recipe);
        updateCarnivore(recipe);
        updateFish(recipe);
        updateGluten(recipe);
        updateGluten(recipe);
        updateLowFat(recipe);
        updateLowCarbs(recipe);
        updateHiProtein(recipe);
    }

    private static void updateVegan(Recipe recipe){
        List<MealComponent> veganComponents = recipe.getMealComponents().stream()
                .filter(comp -> comp.getIngredient().isVegan()).toList();
        recipe.setVegan(veganComponents.size() == recipe.getMealComponents().size());
    }

    private static void updateVegetarian(Recipe recipe){
        List<MealComponent> vegetarianComponents = recipe.getMealComponents().stream()
                .filter(comp -> comp.getIngredient().isVegetarian()).toList();
        recipe.setVegetarian(vegetarianComponents.size() == recipe.getMealComponents().size());
    }

    private static void updateCarnivore(Recipe recipe){
        for(Ingredient ingredient : recipe.getMealComponents().stream().map(MealComponent::getIngredient).toList()){
            if (ingredient.getType() == IngredientType.MEAT || ingredient.getType() == IngredientType.FISH){
                recipe.setCarnivore(true);
                return;
            }
        }
        recipe.setCarnivore(false);
    }

    private static void updateFish(Recipe recipe){
        for(Ingredient ingredient : recipe.getMealComponents().stream().map(MealComponent::getIngredient).toList()){
            if (ingredient.getType() == IngredientType.FISH){
                recipe.setFish(true);
                return;
            }
        }
        recipe.setFish(false);
    }

    private static void updateGluten(Recipe recipe){
        for(Ingredient ingredient : recipe.getMealComponents().stream().map(MealComponent::getIngredient).toList()){
            if (ingredient.isGluten()){
                recipe.setGluten(true);
                return;
            }
        }
        recipe.setGluten(false);
    }

    private static void updateLowFat(Recipe recipe){
        recipe.setLowFat(recipe.getFat() * 9 <= (0.2f * recipe.getCalories()));
    }

    private static void updateLowCarbs(Recipe recipe){
        recipe.setLowCarbs(recipe.getCarbs() * 4 <= (0.15f * recipe.getCalories()));
    }

    private static void updateHiProtein(Recipe recipe){
        recipe.setHiProtein(recipe.getProtein() * 4 >= (0.3f * recipe.getCalories()));
    }
}
