package com.codecool.mrcook.controller.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResult {
    private boolean success = false;
    private boolean usernameTaken;
    private boolean emailTaken;

    public RegisterResult (boolean usernameTaken, boolean emailTaken){
        this.usernameTaken = usernameTaken;
        this.emailTaken = emailTaken;
    }
}
