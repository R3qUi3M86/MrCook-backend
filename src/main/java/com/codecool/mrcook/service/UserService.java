package com.codecool.mrcook.service;

import com.codecool.mrcook.controller.rest.request.RegisterRequest;
import com.codecool.mrcook.controller.rest.response.RegisterResult;
import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.UserDTO;
import com.codecool.mrcook.model.UserRole;
import com.codecool.mrcook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepository.findByEmail(username);
        return userDetails.orElseThrow(() -> new UsernameNotFoundException("User: "+username+" Not found"));
    }

    public RegisterResult registerNewUser(RegisterRequest regRequest){
        if (regRequest.isValid()){
            boolean usernameTaken = userRepository.findByUsername(regRequest.getUsername()).isPresent();
            boolean emailTaken = userRepository.findByEmail(regRequest.getEmail()).isPresent();
            RegisterResult regResult = new RegisterResult(usernameTaken, emailTaken);
            if (!usernameTaken && !emailTaken){
                regResult.setSuccess(true);
                User newUser = User.builder()
                        .username(regRequest.getUsername())
                        .email(regRequest.getEmail())
                        .password(passwordEncoder.encode(regRequest.getPassword()))
                        .member(false)
                        .subscriber(regRequest.isSubscriber())
                        .banned(false)
                        .roles(UserRole.CUSTOMER.toString())
                        .build();
                userRepository.save(newUser);
                log.info("New user registered! {name: "+newUser.getUsername()+", email: "+newUser.getEmail()+"}");
            }
            return regResult;
        }
        return new RegisterResult(false, false,false);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

//    public User getAnonymousUser(){
//        return userRepository.getById((long) 1);
//    }
//
//    public User getDefaultAdminUser(){
//        return userRepository.getById((long) 2);
//    }
//
//    public User getDefaultCustomerMemberUser(){
//        return userRepository.getById((long) 3);
//    }
//
//    public User getDefaultCustomerUser(){
//        return userRepository.getById((long) 4);
//    }
}
