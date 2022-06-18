package com.codecool.codecoolshopspring.service;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.ProductComment;
import com.codecool.codecoolshopspring.model.comments.ProductCommentDTO;
import com.codecool.codecoolshopspring.model.recipes.CookingPhase;
import com.codecool.codecoolshopspring.model.recipes.MealComponent;
import com.codecool.codecoolshopspring.model.recipes.Recipe;
import com.codecool.codecoolshopspring.repository.ProductCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommentService {

    private final ProductCommentRepository productCommentRepository;

    public void createProductComment(ProductComment comment, User user) {
        comment.setUser(user);
        productCommentRepository.save(comment);
        log.info("User commented on product: " + comment);
    }

    public List<ProductCommentDTO> getAll(){
        List<ProductComment> productComments = productCommentRepository.findAll();
        return productComments.stream().map(ProductCommentDTO::new).collect(Collectors.toList());
    }

    public void updateProductComment(ProductComment updatedComment){
        Optional<ProductComment> optOldComment = productCommentRepository.findById(updatedComment.getId());
        if (optOldComment.isPresent()){
            ProductComment oldComment = optOldComment.get();
            oldComment.update(updatedComment);
            productCommentRepository.save(oldComment);
            log.info("User updated product comment: " + oldComment);
        } else {
            log.warn("Cannot update recipe with given Id - does not exist in database!");
        }

    }

    public void deleteProductComment(long id, User user){
        Optional<ProductComment> optProductComment = productCommentRepository.findById(id);
        if (optProductComment.isPresent()){
            user.setProductComment(null);
            productCommentRepository.deleteById(id);
            log.info("User deleted product comment: " + optProductComment.get());
        } else {
            log.warn("Cannot delete product comment with given Id - does not exist in database!");
        }
    }

//    public void upVoteRecipe(long id){
//        Optional<Recipe> optRecipe = recipeRepository.findById(id);
//        if (optRecipe.isPresent()){
//
//        }
//    }
}
