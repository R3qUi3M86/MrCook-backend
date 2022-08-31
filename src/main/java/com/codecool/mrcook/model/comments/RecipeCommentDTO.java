package com.codecool.mrcook.model.comments;

import com.codecool.mrcook.model.user.UserDTO;
import com.codecool.mrcook.model.votes.RecipeCommentVoteDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeCommentDTO {


    private final long id;
    private final String title;
    private final String body;
    private final String author;
    private final long recipeId;
    private List<RecipeCommentVoteDTO> recipeCommentVotes;
    private final Date createDate;
    private final Date modifyDate;

    public RecipeCommentDTO(RecipeComment recipeComment){
        this.id = recipeComment.getId();
        this.title = recipeComment.getTitle();
        this.body = recipeComment.getBody();
        this.author = recipeComment.getUser().getUsername();
        this.recipeId = recipeComment.getRecipe().getId();
        this.recipeCommentVotes = recipeComment.getRecipeCommentVotes().stream()
                .map(RecipeCommentVoteDTO::new)
                .collect(Collectors.toList());
        this.createDate = recipeComment.getCreateDate();
        this.modifyDate = recipeComment.getModifyDate();
    }
}
