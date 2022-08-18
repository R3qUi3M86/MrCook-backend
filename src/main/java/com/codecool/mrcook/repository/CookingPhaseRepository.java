package com.codecool.mrcook.repository;

import com.codecool.mrcook.model.recipes.cookingphase.CookingPhase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookingPhaseRepository extends JpaRepository<CookingPhase, Long> {
}
