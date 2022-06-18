package com.codecool.codecoolshopspring.model.comments;

import com.codecool.codecoolshopspring.model.UserDTO;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProductCommentDTO {


    private final long id;
    private final String title;
    private final String body;
    private final Integer rating;
    private final UserDTO userDTO;
//    private List<ProductCommentVote> productCommentVotes;
    private final Date createDate;
    private final Date modifyDate;

    public ProductCommentDTO(ProductComment productComment) {
        this.id = productComment.getId();
        this.title = productComment.getTitle();
        this.body = productComment.getBody();
        this.rating = productComment.getRating();
        this.userDTO = new UserDTO(productComment.getUser());
        this.createDate = productComment.getCreateDate();
        this.modifyDate = productComment.getModifyDate();
    }
}
