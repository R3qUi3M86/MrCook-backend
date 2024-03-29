package com.codecool.mrcook.model.user;

import com.codecool.mrcook.model.comments.ProductComment;
import com.codecool.mrcook.model.comments.RecipeComment;
import com.codecool.mrcook.model.votes.RecipeVote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 16)
    private String username;

    @Email
    @NotNull
    @Column(length = 100)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean member;

    @NotNull
    private boolean subscriber;

    @NotNull
    private boolean banned;

    @NotNull
    private String roles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_product_comment")
    private ProductComment productComment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeComment> recipeComments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeVote> recipeVotes = new ArrayList<>();


    public User(String username, String email, String password, boolean member, boolean banned, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.member = member;
        this.banned = banned;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getRoles()));
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
