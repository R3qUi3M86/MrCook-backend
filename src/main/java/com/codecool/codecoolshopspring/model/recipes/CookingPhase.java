package com.codecool.codecoolshopspring.model.recipes;

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

    @NotNull
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CookingPhaseName cookingPhaseName;
    private Integer duration;
}
