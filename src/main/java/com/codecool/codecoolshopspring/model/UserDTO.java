package com.codecool.codecoolshopspring.model;

import lombok.Getter;

@Getter
public class UserDTO {

    private long id;
    private String username;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
