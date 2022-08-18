package com.codecool.mrcook.model.recipes.cookingphase;

import lombok.Getter;

@Getter
public enum CookingPhaseName {
    CHOP("Chop", "Posiekaj"),
    GRATE("Grate", "Zetrzyj"),
    BAKE("Bake", "Piecz"),
    SQUEEZE("Squeeze", "Wyciśnij"),
    CRUSH("Crush", "Zgnieć"),
    ROLL("Roll", "Zawiń"),
    MIX("Mix","Wymieszaj"),
    REST("Rest", "Odstaw"),
    CLOSE_MRCOOK("Close MrCook", "Zamknij MrCook'a"),
    OPEN_MRCOOK("Open MrCook", "Otwórz MrCook'a"),
    FORM_SMALL_BALLS("Form small balls", "Uformuj niewielkie kulki"),
    FORM_OVALS("Form oval shapes", "Uformuj owalne kształty"),
    FORM_BALL("Form ball shape", "Uformuj kulę");

    private final String enName;
    private final String plName;

    CookingPhaseName(String enName, String plName) {
        this.enName = enName;
        this.plName = plName;
    }
}
