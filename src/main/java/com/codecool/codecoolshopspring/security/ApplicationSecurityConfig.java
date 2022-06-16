package com.codecool.codecoolshopspring.security;

import com.codecool.codecoolshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true, prePostEnabled = true)
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/js/**", "/css/**", "/img/*").permitAll()
                .antMatchers("/cart/addProduct", "/cart/removeProduct").permitAll()//.hasAuthority("customer")
                .anyRequest().permitAll()//.authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
                .defaultSuccessUrl("/", true)
//                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll();
//        super.configure(http);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

//    @Autowired
//    public void addNewUsers(){
//        userService.putNewUser(new User("stiepan", "stiepan@yahoo.com", passwordEncoder.encode("qwe"), "customer"));
//    }
}
