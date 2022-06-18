package com.codecool.codecoolshopspring.model.recipes.mealcomponent;

import com.codecool.codecoolshopspring.model.recipes.Ingredient;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MealComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="fk_recipe")
    private Recipe recipe;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Ingredient ingredient;

    @NotNull
    private Integer quantity;

    @Override
    public String toString() {
        return "MealComponent{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}
