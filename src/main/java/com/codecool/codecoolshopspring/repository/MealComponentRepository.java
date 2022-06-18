package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.recipes.mealcomponent.MealComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealComponentRepository extends JpaRepository<MealComponent, Long> {
}
