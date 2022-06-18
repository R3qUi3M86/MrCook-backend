package com.codecool.codecoolshopspring.model.comments;

import com.codecool.codecoolshopspring.model.User;
//import com.codecool.codecoolshopspring.model.votes.ProductCommentVote;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    private Integer rating;

    @OneToOne(mappedBy = "productComment")
    private User user;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "product_comment_id")
//    private List<ProductCommentVote> productCommentVotes = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public void update(ProductComment productComment){
        this.title = productComment.getTitle();
        this.body = productComment.getBody();
        this.rating = productComment.getRating();
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", rating=" + rating +
                ", user=" + user.getUsername() +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
