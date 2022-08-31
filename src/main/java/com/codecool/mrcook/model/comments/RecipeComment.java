package com.codecool.mrcook.model.comments;

import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.recipes.Recipe;
import com.codecool.mrcook.model.votes.RecipeCommentVote;
import com.codecool.mrcook.model.votes.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class RecipeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String body;

    @ManyToOne
    @JoinColumn(name="fk_user", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="fk_recipe", nullable = false)
    private Recipe recipe;

    @OneToMany(mappedBy = "recipeComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeCommentVote> recipeCommentVotes = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public void withdrawVote(RecipeCommentVote recipeCommentVote){
        recipeCommentVotes.remove(recipeCommentVote);
    }

    public void castVote(User user, VoteType voteType){
        recipeCommentVotes.add(RecipeCommentVote.builder()
                .voteType(voteType)
                .user(user)
                .recipeComment(this)
                .build());
    }

    public void update(RecipeComment recipeComment){
        this.title = recipeComment.getTitle();
        this.body = recipeComment.getBody();
    }

    @Override
    public String toString() {
        return "RecipeComment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user.getUsername() +
                ", recipe=" + recipe.getId() +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
