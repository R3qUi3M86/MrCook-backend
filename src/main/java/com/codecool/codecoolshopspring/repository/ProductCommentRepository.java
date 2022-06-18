package com.codecool.codecoolshopspring.repository;

import com.codecool.codecoolshopspring.model.comments.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
