package com.codecool.mrcook.model;

import lombok.Getter;

@Getter
public class UserDTO {

    private final long id;
    private final String username;
    private final boolean member;
    private final boolean productComment;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.member = user.isMember();
        this.productComment = user.getProductComment() != null;
    }
}
