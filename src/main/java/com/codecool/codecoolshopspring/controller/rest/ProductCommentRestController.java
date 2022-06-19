package com.codecool.codecoolshopspring.controller.rest;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.ProductComment;
import com.codecool.codecoolshopspring.model.comments.ProductCommentDTO;
import com.codecool.codecoolshopspring.service.ProductCommentService;
import com.codecool.codecoolshopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductCommentRestController {
    private final ProductCommentService productCommentService;
    private final UserService userService;

    @PostMapping("/product_comment/add")
    public void addProductComment(@RequestBody ProductComment comment){
        User user = userService.getDefaultAdminUser();
        productCommentService.createProductComment(comment, user);
    }

    @GetMapping("/product_comment/get_all")
    public List<ProductCommentDTO> getAllProductComments(){
        return productCommentService.getAll();
    }

    @PutMapping("/product_comment/update")
    public void updateProductComment(@RequestBody ProductComment comment){
        productCommentService.updateProductComment(comment);
    }

    @DeleteMapping("/product_comment/delete/{id}")
    public void deleteProductComment(@PathVariable String id){
        User user = userService.getDefaultAdminUser();
        productCommentService.deleteProductComment(Long.parseLong(id), user);
    }
}
