package com.codecool.codecoolshopspring.model.recipes;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import com.codecool.codecoolshopspring.utilities.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private User user;

    @NotNull
    private String title;

    @NotNull
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MealComponent> mealComponents;

    @NotNull
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CookingPhase> cookingPhases;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn
//    private List<RecipeComment> recipeComments = new ArrayList<>();

    private byte[] image;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", description='" + title + '\'' +
                ", mealComponents=" + mealComponents +
                ", cookingPhases=" + cookingPhases +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
