package com.codecool.mrcook.model.comments;

import lombok.Getter;

import java.util.Date;

@Getter
public class ProductCommentDTO {


    private final long id;
    private final String title;
    private final String body;
    private final Integer rating;
    private final String username;
    private final Date createDate;
    private final Date modifyDate;

    public ProductCommentDTO(ProductComment productComment) {
        this.id = productComment.getId();
        this.title = productComment.getTitle();
        this.body = productComment.getBody();
        this.rating = productComment.getRating();
        this.username = productComment.getUser().getUsername();
        this.createDate = productComment.getCreateDate();
        this.modifyDate = productComment.getModifyDate();
    }
}
