package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.controller.rest.request.AuthCredentialsRequest;
import com.codecool.mrcook.controller.rest.request.RegisterRequest;
import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.UserDTO;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/user/get_current")
    public UserDTO getCurrentUser(@CurrentUser User user){
        return new UserDTO(user);
    }

//    @PostMapping("/user/register")
//    public UserDTO register(@RequestBody RegisterRequest request){
//        return new UserDTO(user);
//    }
}
