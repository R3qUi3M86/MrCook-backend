package com.codecool.mrcook.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private final long id;
    private final String username;
    private boolean member;
    private boolean banned;
    private String roles;
    private boolean productComment;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.member = user.isMember();
        this.banned = user.isBanned();
        this.roles = user.getRoles();
        this.productComment = user.getProductComment() != null;
    }
}
