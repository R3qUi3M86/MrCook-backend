package com.codecool.codecoolshopspring.model.recipes.mealcomponent;

import com.codecool.codecoolshopspring.model.recipes.Ingredient;
import lombok.Getter;

@Getter
public class MealComponentDTO {

    private final long id;
    private final Ingredient ingredient;
    private final Integer quantity;

    public MealComponentDTO(MealComponent mealComponent){
        this.id = mealComponent.getId();
        this.ingredient = mealComponent.getIngredient();
        this.quantity = mealComponent.getQuantity();
    }
}
