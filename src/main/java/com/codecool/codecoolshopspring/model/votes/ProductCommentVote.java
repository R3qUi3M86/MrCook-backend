package com.codecool.codecoolshopspring.model.votes;

import com.codecool.codecoolshopspring.model.User;
import com.codecool.codecoolshopspring.model.comments.ProductComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class ProductCommentVote {

    @Id
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_product_comment", nullable = false)
    private ProductComment productComment;

    @Override
    public String toString() {
        return "ProductCommentVote{" +
                "id=" + id +
                ", voteType=" + voteType +
                ", user=" + user.getUsername() +
                ", productComment=" + productComment.getId() +
                '}';
    }
}
