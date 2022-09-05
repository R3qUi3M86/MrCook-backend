package com.codecool.mrcook.model.recipes;

import lombok.Getter;

@Getter
public enum Ingredient {
    //VEGETABLES
    BROCCOLI("Broccoli", "Brokuły", IngredientType.VEGETABLE,
            true, true, false,
            31, 3f, 0.4f, 2.7f),
    TOMATO("Tomato", "Pomidor", IngredientType.VEGETABLE,
            true, true, false,
            19, 0.9f, 0.2f, 2.9f),
    ONION("Onion", "Cebula", IngredientType.VEGETABLE,
            true, true, false,
            33, 1.4f, 0.4f, 5.2f),
    PAPRIKA("Paprika", "Papryka", IngredientType.VEGETABLE,
            true, true, false,
            32, 1.3f, 0.5f, 4.6f),

    //FRUITS
    APPLE("Apple", "Jabłka", IngredientType.FRUIT,
            true, true, false,
            50, 0.4f, 0.4f, 10.1f),

    //SPICES
    SALT("Salt", "Sól", IngredientType.SPICE,
            true, true, false,
            0, 0f, 0f, 0f),
    PEPPER("Pepper", "Pierpz", IngredientType.SPICE,
            true, true, false,
            255, 11f, 3.3f, 64.8f),

    //MEATS
    CHICKEN_BREAST("Chicken breast", "Pierś z kurczaka", IngredientType.MEAT,
            false, false, false,
            98, 21.5f, 1.3f, 0f),

    //FISH
    SALMON_FILLET("Salmon fillet", "Filet z łososia", IngredientType.FISH,
            false, false, false,
            201, 19.9f, 13.6f, 0f),

    //DAIRY
    PARMESAN_CHEESE("Parmesan cheese", "Ser Parmezan", IngredientType.DAIRY,
            true, false, false,
            454, 40.7f, 32.0f, 0.1f),

    //GRAIN
    WHEAT_FLOUR("Wheat flour", "Mąka pszenna", IngredientType.GRAIN,
            true, true, true,
            342, 12.7f, 1.8f, 68.4f),

    //FAT
    OLIVE_OIL("Olive oil", "Oliwa z oliwek", IngredientType.FAT,
            true, true, false,
            882, 0f, 99.6f, 0.2f),

    //NUTS
    ENGLISH_WALNUTS("English walnuts", "Orzechy włoskie", IngredientType.NUT,
            true, true, false,
            666, 18.0f, 60.3f, 11.5f),

    //OTHER
    HONEY("Honey", "Miód", IngredientType.OTHER,
            true, false, false,
            324, 0.3f, 0f, 79.5f),
    WATER("Water", "Woda", IngredientType.OTHER,
            true, true, false,
            0, 0f, 0f, 0f),
    YEAST("Yeast", "Drożdże", IngredientType.OTHER,
          true, true, false,
            92, 11.3f, 2f, 14.5f);

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
