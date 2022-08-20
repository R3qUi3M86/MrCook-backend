package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.UserDTO;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/user/get_current")
    public UserDTO getCurrentUser(){
        User user = userService.getDefaultAdminUser(); //To be changed
        return new UserDTO(user);
    }
}
