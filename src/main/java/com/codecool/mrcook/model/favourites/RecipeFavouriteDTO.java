package com.codecool.mrcook.model.favourites;

import lombok.Getter;

@Getter
public class RecipeFavouriteDTO {

    private final long userId;

    public RecipeFavouriteDTO(RecipeFavourite recipeFavourites){
        this.userId = recipeFavourites.getUser().getId();
    }
}
