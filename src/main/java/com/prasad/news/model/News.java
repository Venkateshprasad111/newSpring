package com.prasad.news.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @Column(name = "news_id")
    private UUID newsId=UUID.randomUUID();
    @Column(name = "news_description")
    private String newsDescription;


    @Column(name = "news_topic")
    private String newsTopic;

    @ManyToOne
    @JoinColumn(
            name = "news_type",
            referencedColumnName = "news_type"

    )
    private NewsType newsType;


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

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Images> images = new ArrayList<Images>();
}
