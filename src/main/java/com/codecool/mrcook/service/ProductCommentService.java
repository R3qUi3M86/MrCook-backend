package com.codecool.mrcook.service;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.comments.ProductComment;
import com.codecool.mrcook.model.comments.ProductCommentDTO;
import com.codecool.mrcook.repository.ProductCommentRepository;
import com.codecool.mrcook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final UserRepository userRepository;

    public boolean createProductComment(ProductComment comment, User user) {
        if (user.getProductComment() == null) {
            user.setProductComment(comment);
            userRepository.save(user);
            comment.setUser(user);
            log.info("User commented on product: " + comment);
            return true;
        } else {
            log.warn("User tried to comment product more than once. Comment discarded!");
            return false;
        }
    }

    public List<ProductCommentDTO> getAll(){
        List<ProductComment> productComments = productCommentRepository
                .findAll(Sort.by(Sort.Direction.DESC, "rating"));
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
            log.warn("Cannot update product comment id=" + updatedComment.getId() + " - does not exist in database!");
        }

    }

    public void deleteProductComment(User user){
        log.info("User deleted product comment: " + user.getProductComment());
        user.setProductComment(null);
        userRepository.save(user);
    }

    public boolean deleteProductCommentByAdmin(long id){
        Optional<ProductComment> optProductComment = productCommentRepository.findById(id);
        if (optProductComment.isPresent()){
            User user = optProductComment.get().getUser();
            user.setProductComment(null);
            userRepository.save(user);
            log.info("Admin deleted product comment: " + optProductComment.get());
            return true;
        } else {
            log.warn("Cannot delete product comment id=" + id + " - does not exist in database!");
            return false;
        }
    }

//    public void upVoteRecipe(long id){
//        Optional<Recipe> optRecipe = recipeRepository.findById(id);
//        if (optRecipe.isPresent()){
//
//        }
//    }
}
