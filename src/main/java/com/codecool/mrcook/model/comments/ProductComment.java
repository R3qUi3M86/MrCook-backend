package com.codecool.mrcook.model.comments;

import com.codecool.mrcook.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


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
