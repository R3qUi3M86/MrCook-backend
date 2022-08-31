package com.codecool.mrcook.model.favourites;
import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeFavourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_recipe", nullable = false)
    private Recipe recipe;

    @Override
    public String toString() {
        return "RecipeFavourite{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", recipe=" + recipe.getId() +
                '}';
    }
}

