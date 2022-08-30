package com.codecool.mrcook;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void encodePassword(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode("pass3");
        System.out.println(hash);
    }
}
