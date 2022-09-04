package com.codecool.mrcook.model.recipes;

import lombok.Getter;

@Getter
public enum Ingredient {
    TOMATO("Tomato", "Pomidor", IngredientType.VEGETABLE,
            true, true, false,
            19, 0.9f, 0.2f, 2.9f),
    ONION("Onion", "Cebula", IngredientType.VEGETABLE,
            true, true, false,
            33, 1.4f, 0.4f, 5.2f),
    CHICKEN_BREAST("Chicken breast", "Pierś z kurczaka", IngredientType.MEAT,
            false, false, false,
            98, 21.5f, 1.3f, 0f);

    private final String enName;
    private final String plName;
    private final IngredientType type;
    private final boolean vegetarian;
    private final boolean vegan;
    private final boolean gluten;
    private final int calories;
    private final float protein;
    private final float carbs;
    private final float fat;

    Ingredient(String enName, String plName, IngredientType type, boolean vegetarian, boolean vegan, boolean gluten,
                int calories, float protein, float fat, float carbs) {
        this.enName = enName;
        this.plName = plName;
        this.type = type;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.gluten = gluten;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}
