package com.codecool.codecoolshopspring.model.comments;

import com.codecool.codecoolshopspring.model.UserDTO;
import lombok.Getter;

import java.util.Date;

@Getter
public class RecipeCommentDTO {


    private final long id;
    private final String title;
    private final String body;
    private final UserDTO userDTO;
    private final long recipeId;

//    @OneToMany(mappedBy = "recipe_comment")
//    private List<RecipeCommentVote> recipeCommentVotes  = new ArrayList<>();

    private final Date createDate;
    private final Date modifyDate;

    public RecipeCommentDTO(RecipeComment recipeComment){
        this.id = recipeComment.getId();
        this.title = recipeComment.getTitle();
        this.body = recipeComment.getBody();
        this.userDTO = new UserDTO(recipeComment.getUser());
        this.recipeId = recipeComment.getRecipe().getId();
        this.createDate = recipeComment.getCreateDate();
        this.modifyDate = recipeComment.getModifyDate();
    }
}
