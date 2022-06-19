package com.codecool.codecoolshopspring.model.votes;

import lombok.Getter;

@Getter
public class RecipeVoteDTO {

    private final long id;
    private final VoteType voteType;
    private final long userId;

    public RecipeVoteDTO(RecipeVote recipeVote){
        this.id = recipeVote.getId();
        this.voteType = recipeVote.getVoteType();
        this.userId = recipeVote.getUser().getId();
    }
}
