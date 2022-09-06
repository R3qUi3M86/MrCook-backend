package com.codecool.mrcook.model.recipes.helpers;

import com.codecool.mrcook.model.recipes.Recipe;

public class RecipeMacroCalculator {
    public static void updateMacronutrients(Recipe recipe){
        updateCalories(recipe);
        updateProtein(recipe);
        updateCarbs(recipe);
        updateFat(recipe);
    }

    private static void updateCalories(Recipe recipe){
        recipe.setCalories(recipe.getMealComponents().stream()
                .map(comp -> comp.getIngredient().getCalories() * comp.getQuantity() / 100)
                .reduce(0, Integer::sum));
    }

    private static void updateProtein(Recipe recipe){
        recipe.setProtein(recipe.getMealComponents().stream()
                .map(comp -> comp.getIngredient().getProtein() * comp.getQuantity() / 100)
                .reduce(0f, Float::sum));
    }

    private static void updateCarbs(Recipe recipe){
        recipe.setCarbs(recipe.getMealComponents().stream()
                .map(comp -> comp.getIngredient().getCarbs() * comp.getQuantity() / 100)
                .reduce(0f, Float::sum));
    }

    private static void updateFat(Recipe recipe){
        recipe.setFat(recipe.getMealComponents().stream()
                .map(comp -> comp.getIngredient().getFat() * comp.getQuantity() / 100)
                .reduce(0f, Float::sum));
    }
}
