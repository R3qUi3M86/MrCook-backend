package com.codecool.codecoolshopspring.model.votes;

import lombok.Getter;

@Getter
public class RecipeCommentVoteDTO {

    private final long id;
    private final VoteType voteType;
    private final long userId;

    public RecipeCommentVoteDTO(RecipeCommentVote recipeCommentVote){
        this.id = recipeCommentVote.getId();
        this.voteType = recipeCommentVote.getVoteType();
        this.userId = recipeCommentVote.getUser().getId();
    }
}
