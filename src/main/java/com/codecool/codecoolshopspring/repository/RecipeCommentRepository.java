package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.comments.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {
}
