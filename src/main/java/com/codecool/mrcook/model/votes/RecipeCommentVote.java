package com.codecool.mrcook.model.votes;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.comments.RecipeComment;
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
public class RecipeCommentVote {

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
