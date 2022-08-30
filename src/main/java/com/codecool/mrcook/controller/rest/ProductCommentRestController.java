package com.codecool.mrcook.controller.rest;

import com.codecool.mrcook.model.User;
import com.codecool.mrcook.model.UserDTO;
import com.codecool.mrcook.model.comments.ProductComment;
import com.codecool.mrcook.model.comments.ProductCommentDTO;
import com.codecool.mrcook.security.CurrentUser;
import com.codecool.mrcook.service.ProductCommentService;
import com.codecool.mrcook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductCommentRestController {
    private final ProductCommentService productCommentService;
    private final UserService userService;

    @PostMapping("/product_comment/add")
    public ResponseEntity<?> addProductComment(@RequestBody ProductComment comment, @CurrentUser User user){
        if (productCommentService.createProductComment(comment, user))
            return ResponseEntity.ok().body(new UserDTO(user));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping("/product_comment/get_all")
    public List<ProductCommentDTO> getAllProductComments(){
        return productCommentService.getAll();
    }

    @PutMapping("/product_comment/update")
    public ResponseEntity<?> updateProductComment(@RequestBody ProductComment comment, @CurrentUser User user){
        if (user.getProductComment() != null) {
            if (comment.getId() == user.getProductComment().getId()) {
                productCommentService.updateProductComment(comment);
                return ResponseEntity.ok().build();
            }
            log.warn("Cannot update product comment id=" + comment.getId() + " - insufficient access rights!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @DeleteMapping("/product_comment/delete/{id}")
    public ResponseEntity<?> deleteProductComment(@PathVariable String id, @CurrentUser User user){
        if (Objects.equals(user.getRoles(), "ADMIN")){
            if (productCommentService.deleteProductCommentByAdmin(Long.parseLong(id)))
                return ResponseEntity.ok().body(new UserDTO((User)userService.loadUserByUsername(user.getEmail())));
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } else if (user.getProductComment() != null) {
            if  (user.getProductComment().getId() == Long.parseLong(id)) {
                productCommentService.deleteProductComment(user);
                return ResponseEntity.ok().body(new UserDTO(user));
            }
            log.warn("Cannot delete product comment id=" + id + " - insufficient access rights!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
