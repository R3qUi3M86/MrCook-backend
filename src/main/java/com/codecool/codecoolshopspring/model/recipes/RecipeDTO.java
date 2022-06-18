package com.codecool.codecoolshopspring.model.recipes;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.UserDTO;
import lombok.Getter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class RecipeDTO {

    private long id;
    private UserDTO userDTO;
    private String title;
    private List<MealComponent> mealComponents;
    private List<CookingPhase> cookingPhases;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn
//    private List<RecipeComment> recipeComments = new ArrayList<>();

    private byte[] image;

    public RecipeDTO(Recipe recipe){
        this.id = recipe.getId();
        this.userDTO = new UserDTO(recipe.getUser());
        this.title = recipe.getTitle();
        this.mealComponents = recipe.getMealComponents();
        this.cookingPhases = recipe.getCookingPhases();
        this.image = recipe.getImage();
    }
}
