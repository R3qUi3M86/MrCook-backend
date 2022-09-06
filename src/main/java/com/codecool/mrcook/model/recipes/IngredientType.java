package com.codecool.mrcook.model.recipes;

import lombok.Getter;

@Getter
public enum IngredientType {
    VEGETABLE("Vegetable", "Warzywo"),
    FRUIT("Fruit", "Owoc"),
    SPICE("Spice", "Przyprawa"),
    MEAT("Meat", "Mięso"),
    FISH("Fish/Seafood", "Ryba/Owoce morza"),
    DAIRY("Dairy", "Nabiał"),
    GRAIN("Grain", "Ziarno"),
    FAT("Fat", "Tłuszcz"),
    NUT("Nut", "Orzech"),
    OTHER("Other", "Inne");

    private final String enName;
    private final String plName;

    IngredientType(String enName, String plName) {
        this.enName = enName;
        this.plName = plName;
    }
}
