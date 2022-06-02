package com.codecool.codecoolshopspring.model;

import com.codecool.codecoolshopspring.utilities.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;

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
    private String ingredients;
    @NotNull
    private String description;
    @NotNull
    private byte[] image;

    public Recipe(User user, String ingredients, String description, Image image) throws IOException {
        this.user = user;
        this.ingredients = ingredients;
        this.description = description;
        this.image = ImageConverter.convertImgToBytea(image);
    }
}
