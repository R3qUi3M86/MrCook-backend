package com.codecool.mrcook.model.recipes;

import lombok.Getter;

@Getter
public enum Ingredient {
    TOMATO("Tomato", "Pomidor", IngredientType.VEGETABLE, true, true),
    ONION("Onion", "Cebula", IngredientType.VEGETABLE, true, true),
    CHICKEN_BREAST("Chicken breast", "Pierś z kurczaka", IngredientType.MEAT, false, false);

    private final String enName;
    private final String plName;
    private final IngredientType type;
    private final boolean vegetarian;
    private final boolean vegan;

    Ingredient(String enName, String plName, IngredientType type, boolean vegetarian, boolean vegan) {
        this.enName = enName;
        this.plName = plName;
        this.type = type;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }
}
