package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.Recipe;
import com.codecool.codecoolshopspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<User, Integer> {
    Optional<Recipe> findByUsername(String username);
}
