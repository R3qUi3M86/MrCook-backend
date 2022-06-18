package com.codecool.codecoolshopspring.model.comments;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.UserDTO;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class ProductCommentDTO {


    private long id;
    private String title;
    private String body;
    private Integer rating;
    private UserDTO userDTO;
//    private List<ProductCommentVote> productCommentVotes;
    private Date createDate;
    private Date modifyDate;

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
