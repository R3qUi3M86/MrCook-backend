package com.codecool.mrcook.repository;

import com.codecool.mrcook.model.recipes.mealcomponent.MealComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealComponentRepository extends JpaRepository<MealComponent, Long> {
}
