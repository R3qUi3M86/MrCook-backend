package com.codecool.codecoolshopspring.model.recipes.cookingphase;

import com.codecool.codecoolshopspring.model.recipes.Ingredient;
import lombok.Getter;

import java.util.List;

@Getter
public class CookingPhaseDTO {

    private final long id;
    private final Integer step;
    private final List<Ingredient> ingredients;
    private final CookingPhaseName cookingPhaseName;
    private final Integer duration;

    public CookingPhaseDTO(CookingPhase cookingPhase){
        this.id = cookingPhase.getId();
        this.step = cookingPhase.getStep();
        this.ingredients = cookingPhase.getIngredients();
        this.cookingPhaseName = cookingPhase.getCookingPhaseName();
        this.duration = cookingPhase.getDuration();
    }
}
