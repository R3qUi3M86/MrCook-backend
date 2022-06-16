package com.codecool.codecoolshopspring.model.recipes;

import com.codecool.codecoolshopspring.model.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String description;

    @NotNull
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MealComponent> mealComponents;

    @NotNull
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CookingPhase> cookingPhases;

    private byte[] image;

    public Recipe(String description, User user, List<MealComponent> mealComponents, List<CookingPhase> cookingPhases, Image image) throws IOException {
        this.user = user;
        this.description = description;
        this.mealComponents = mealComponents;
        this.cookingPhases = cookingPhases;
        this.image = ImageConverter.convertImgToBytea(image);
    }

    public Recipe(String description, User user, List<MealComponent> mealComponents, List<CookingPhase> cookingPhases) {
        this.user = user;
        this.description = description;
        this.mealComponents = mealComponents;
        this.cookingPhases = cookingPhases;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", description='" + description + '\'' +
                ", mealComponents=" + mealComponents +
                ", cookingPhases=" + cookingPhases +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
