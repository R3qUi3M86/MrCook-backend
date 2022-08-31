package com.codecool.mrcook.model.votes;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_recipe", nullable = false)
    private Recipe recipe;

    @Override
    public String toString() {
        return "RecipeVote{" +
                "id=" + id +
                ", voteType=" + voteType +
                ", user=" + user.getUsername() +
                ", recipe=" + recipe.getId() +
                '}';
    }
}
