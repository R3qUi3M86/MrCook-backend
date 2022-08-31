package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.controller.rest.request.AuthCredentialsRequest;
import com.codecool.mrcook.controller.rest.request.RegisterRequest;
import com.codecool.mrcook.controller.rest.response.RegisterResult;
import com.codecool.mrcook.model.user.User;
import com.codecool.mrcook.model.user.UserDTO;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.security.TokenProvider;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authManager;

    @GetMapping("/user/get_current")
    public UserDTO getCurrentUser(@CurrentUser User user){
        return new UserDTO(user);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        RegisterResult registerResult = userService.registerNewUser(request);
        if (registerResult.isSuccess())
            return ResponseEntity.ok().body(registerResult);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(registerResult);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request){
        try {
            Authentication authentication = authManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(), request.getPassword()
                            )
                    );
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            tokenProvider.createToken(authentication)
                    )
                    .body(new UserDTO(user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
