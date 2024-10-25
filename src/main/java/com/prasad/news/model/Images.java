package com.prasad.news.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Images {

    @Id
    private UUID imageId= UUID.randomUUID();

    @ManyToOne
    @JoinColumn(
            name = "news_id",
            referencedColumnName = "news_id"
    )
    private News news;

    @Column(name = "image-url")
    private String imageUrl;

    @Column(name = "image_credits")
    private String imageCredits;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date createdOn;
    @ManyToOne
    @JoinColumn(name = "created_by",
            referencedColumnName = "user_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by",
            referencedColumnName = "user_id")
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;

}
