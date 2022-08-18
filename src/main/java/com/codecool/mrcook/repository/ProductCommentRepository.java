package com.codecool.mrcook.repository;

import com.codecool.mrcook.model.comments.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
