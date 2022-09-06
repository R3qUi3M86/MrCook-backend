package com.codecool.mrcook.controller.rest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private boolean subscriber;

    public boolean isValid(){
        return userNameIsValid() && emailIsValid() && passwordIsValid();
    }

    private boolean userNameIsValid(){
        return username.matches("[a-zA-Z\\d-_]+") && username.length() > 1 && username.length() <= 16;
    }

    private boolean emailIsValid(){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find() && email.length() <= 100;
    }

    private boolean passwordIsValid(){
        return password.matches("[a-zA-Z\\d!@#$%^&*]+") && password.length() >= 8;
    }
}
