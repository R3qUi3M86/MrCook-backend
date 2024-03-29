package com.codecool.mrcook.model.recipes.cookingphase;

import com.codecool.mrcook.model.recipes.Ingredient;
import com.codecool.mrcook.model.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CookingPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Integer step;

    @ManyToOne
    @JoinColumn(name="fk_recipe", nullable=false)
    private Recipe recipe;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CookingPhaseName cookingPhaseName;
    private Integer duration;

    @Override
    public String toString() {
        return "CookingPhase{" +
                "id=" + id +
                ", step=" + step +
                ", ingredients=" + ingredients +
                ", cookingPhaseName=" + cookingPhaseName +
                ", duration=" + duration +
                '}';
    }
}
