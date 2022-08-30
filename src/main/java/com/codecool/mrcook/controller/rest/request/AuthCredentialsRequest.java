package com.codecool.mrcook.controller.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialsRequest {

    private String email;
    private String password;


}
