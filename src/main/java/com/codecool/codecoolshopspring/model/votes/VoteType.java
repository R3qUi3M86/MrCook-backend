package com.codecool.codecoolshopspring.model.votes;

public enum VoteType {
    UP, DOWN;

    public VoteType getOppositeType(VoteType voteType){
        if (voteType == VoteType.UP){
            return VoteType.DOWN;
        } else {
            return VoteType.UP;
        }
    }
}
