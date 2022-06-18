package com.codecool.codecoolshopspring.model.comments;

import com.codecool.codecoolshopspring.model.User;
//import com.codecool.codecoolshopspring.model.votes.ProductCommentVote;
//import com.codecool.codecoolshopspring.model.votes.RecipeCommentVote;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import lombok.AllArgsConstructor;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user", nullable=false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name="recipe_id", nullable=false)
//    private Recipe recipe;

//    @OneToMany(mappedBy = "recipe_comment")
//    private List<RecipeCommentVote> recipeCommentVotes;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public RecipeComment(String title, String body, User user, Recipe recipe) {
        this.title = title;
        this.body = body;
        this.user = user;
//        this.recipe = recipe;
//        this.recipeCommentVotes = new ArrayList<>();
    }
}
