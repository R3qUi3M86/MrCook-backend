package com.codecool.codecoolshopspring.model.votes;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class RecipeCommentVote {

    @Id
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_comment", nullable = false)
    private RecipeComment recipeComment;

    @Override
    public String toString() {
        return "RecipeCommentVote{" +
                "id=" + id +
                ", voteType=" + voteType +
                ", user=" + user.getUsername() +
                ", recipeComment=" + recipeComment.getId() +
                '}';
    }
}
