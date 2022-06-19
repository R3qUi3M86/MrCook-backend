package com.codecool.codecoolshopspring.service;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepository.findByUsername(username);
        return userDetails.orElseThrow(() -> new UsernameNotFoundException("User: "+username+" Not found"));
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public User getDefaultAdminUser(){
        return userRepository.getById((long) 1);
    }

    public User getDefaultCustomerUser(){
        return userRepository.getById((long) 2);
    }
}
